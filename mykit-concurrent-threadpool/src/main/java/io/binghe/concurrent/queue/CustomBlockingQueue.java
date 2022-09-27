/**
 * Copyright 2020-9999 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.binghe.concurrent.queue;

import io.binghe.concurrent.policy.RejectHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description 阻塞队列实现类
 */
public class CustomBlockingQueue<T> implements CustomQueue<T> {

    //ArrayBlockingQueue 队列
    private BlockingQueue<T> queue;

    //ReentrantLock锁
    private ReentrantLock lock = new ReentrantLock();

    //消费者可以消费
    private Condition notEmpty = lock.newCondition();

    //生产者可以生产
    private Condition notFull = lock.newCondition();

    //队列容量
    private int capcity;

    //拒绝策略
    private RejectHandler rejectHandler;

    public CustomBlockingQueue(int capcity){
        if (capcity <= 0){
            throw new IllegalArgumentException();
        }
        this.capcity = capcity;
        this.queue = new ArrayBlockingQueue<T>(capcity);
    }

    @Override
    public T poll(long timeout, TimeUnit timeUnit) {
        lock.lock();
        try{
            //转化为纳秒
            long nanos = timeUnit.toNanos(timeout);
            //是否为空
            while (queue.isEmpty()){
                if (nanos <= 0){
                    return null;
                }
                nanos = notEmpty.awaitNanos(nanos);
            }
            T t = queue.remove();
            notFull.signal();
            return t;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    @Override
    public T take() {
        lock.lock();
        try{
            while (queue.isEmpty()){
                notEmpty.await();
            }
            T t = queue.remove();
            notFull.signal();
            return t;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return null;
    }

    @Override
    public void put(T task) {
        lock.lock();
        try{
            while (queue.size() == capcity){
                notFull.await();
            }
            queue.add(task);
            notEmpty.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    @Override
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try{
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capcity){
                if (nanos <= 0){
                    return false;
                }
                nanos = notFull.awaitNanos(nanos);
            }
            queue.add(task);
            notEmpty.signal();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return false;
    }

    @Override
    public int size() {
        lock.lock();
        try{
            return queue.size();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public int capcity() {
        return capcity;
    }
}
