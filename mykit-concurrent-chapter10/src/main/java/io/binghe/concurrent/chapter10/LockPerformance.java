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

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.locks.*;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 对比ReentrantLock可重入锁，ReadWriteLock读写锁
 *              和StampedLock读写锁对数据进行读写操作的性能
 */
@State(Scope.Group)
public class LockPerformance {
    private int count = 0;
    //ReentrantLock可重入锁
    private final Lock lock = new ReentrantLock();
    //ReadWriteLock读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //读锁
    private final Lock readLock = readWriteLock.readLock();
    //写锁
    private final Lock writeLock = readWriteLock.writeLock();
    //StampedLock锁
    private final StampedLock stampedLock = new StampedLock();

    //使用ReentrantLock锁写数据
    public void lockWrite(){
        lock.lock();
        try{
            count++;
        }finally {
            lock.unlock();
        }
    }

    //使用ReentrantLock锁读数据
    public int lockRead(){
        lock.lock();
        try {
            return count;
        }finally {
            lock.unlock();
        }
    }

    //使用ReadWriteLock的写锁写数据
    public void readWriteLockWrite(){
        writeLock.lock();
        try{
            count++;
        }finally {
            writeLock.unlock();
        }
    }

    //使用ReadWriteLock的读锁读数据
    public int readWriteLockRead(){
        readLock.lock();
        try{
            return count;
        }finally {
            readLock.unlock();
        }
    }

    //使用StampedLock写数据
    public void stampedLockWrite(){
        long stamped = stampedLock.writeLock();
        try {
            count++;
        }finally {
            stampedLock.unlockWrite(stamped);
        }
    }

    //使用StampedLock读数据
    public int stampedLockRead(){
        long stamped = stampedLock.readLock();
        try {
            return count;
        }finally {
            stampedLock.unlockRead(stamped);
        }
    }

    //使用StampedLock乐观读数据
    public int stampedLockOptimisticRead(){
        long stamped = stampedLock.tryOptimisticRead();
        if (!stampedLock.validate(stamped)){
            stamped = stampedLock.readLock();
            try {
                return count;
            }finally {
                stampedLock.unlockRead(stamped);
            }
        }
        return count;
    }
}
