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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 验证StampedLock在多个线程同时读数据的过程中
 *              也允许某个线程对数据进行写操作。
 */
public class StampedLockTest {

    //共享变量
    private int count = 0;
    //StampedLock锁
    private StampedLock stampedLock = new StampedLock();

    //对共享变量进行写操作
    public void write() {
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName()+"--写线程修改共享变量的值开始");
        try{
            count += 1;
        }finally {
            stampedLock.unlockWrite(stamp);
        }
        System.out.println(Thread.currentThread().getName()+"--写线程修改共享变量的值结束");
    }



    //StampedLock提供的乐观读，在读的过程中也允许某个线程进行写操作
    public void optimisticRead(){
        long stamp = stampedLock.tryOptimisticRead();
        int result = count;
        System.out.println(Thread.currentThread().getName()+ "--线程开始检测到共享变量的值是否未被修改过(true无修改，false有修改)：" + stampedLock.validate(stamp));
        System.out.println(Thread.currentThread().getName()+ "--线程开始读取到的共享变量的值：" + result);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ "--线程2秒后检测到共享变量的值是否未被修改过(true无修改，false有修改)：" + stampedLock.validate(stamp));
        if(!stampedLock.validate(stamp)){
            System.out.println("其他线程已经修改了共享变量的数据");
            //从乐观读升级为悲观读锁
            stamp = stampedLock.readLock();
            try{
                System.out.println(Thread.currentThread().getName()+ "--线程从乐观读升级为悲观读");
                result = count;
                System.out.println(Thread.currentThread().getName()+ "--线程从乐观读升级为悲观读后的共享变量的值："+result);
            }finally {
                stampedLock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName()+"--线程读取到的最终的共享变量的值："+result);
    }


    public static void main(String[] args) throws InterruptedException {
        StampedLockTest stampedLockTest = new StampedLockTest();
        //读线程
        new Thread(() -> {
            stampedLockTest.optimisticRead();
        },"Thread-Read").start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("1秒后启动写线程修改共享变量的值");
        //写线程
        new Thread(() -> {
            stampedLockTest.write();
        },"Thread-Write").start();
    }
}
