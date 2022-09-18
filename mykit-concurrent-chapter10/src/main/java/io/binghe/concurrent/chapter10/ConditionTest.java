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
package io.binghe.concurrent.chapter10;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 线程阻塞与唤醒案例
 */
public class ConditionTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        //创建并启动子线程
        new Thread(new ConditionTask(lock, condition)).start();
        //主线程休眠1秒
        Thread.sleep(1000);
        //主线程获取锁
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "--线程唤醒被阻塞的线程开始：" + new Date());
            //唤醒被阻塞的线程
            condition.signal();
            System.out.println(Thread.currentThread().getName() + "--线程唤醒被阻塞的线程结束：" + new Date());
        }finally {
            lock.unlock();
        }
    }
}
