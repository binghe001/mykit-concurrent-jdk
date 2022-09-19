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
package io.binghe.concurrent.chapter11;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description 综合对比synchronized锁、ReentrantLock锁
 *              和AtomicInteger更新int类型数据的性能
 */
@State(Scope.Group)
public class IntPerformance {

    //参与测试的int类型的值
    private int count;
    //ReentrantLock锁
    private final Lock lock = new ReentrantLock();
    //AtomicInteger原子类
    private AtomicInteger atomicInteger = new AtomicInteger();

    //使用ReentrantLock加锁
    public void lockCount(){
        lock.lock();
        try{
            count ++;
        }finally {
            lock.unlock();
        }
    }

    //使用synchronized加锁
    public void syncCount(){
        synchronized (this){
            count ++;
        }
    }

    //使用AtomicInteger
    public void atomicCount(){
        atomicInteger.incrementAndGet();
    }
}
