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

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 读写锁案例
 */
public class ReadWriteLockTest {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public void read(){
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "--线程获取到读锁");
            Thread.sleep(200);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName() + "--线程释放读锁");
            readLock.unlock();
        }
    }

    public void write() {
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "--线程获取到写锁");
            Thread.sleep(200);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName() + "--线程释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args){
        ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
        IntStream.range(0, 5).forEach((i) -> {
            new Thread(() -> {
                readWriteLockTest.read();
            }).start();
        });
        IntStream.range(0, 5).forEach((i) -> {
            new Thread(() -> {
                readWriteLockTest.write();
            }).start();
        });
    }
}
