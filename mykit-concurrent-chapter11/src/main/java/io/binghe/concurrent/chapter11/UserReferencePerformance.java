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

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author binghe(公众号 ： 冰河技术)
 * @version 1.0.0
 * @description 锁与字段类型原子类性能对比案例
 */
@State(Scope.Group)
public class UserReferencePerformance {

    //User对象实例，用于synchronized锁
    private User syncUser = new User("冰河", 18);
    //User对象实例，用于ReentrantLock锁
    private User lockUser = new User("冰河", 18);
    //User对象实例，用于AtomicReferenceFieldUpdater
    private User atomicUser = new User("冰河", 18);
    //ReentrantLock锁
    private Lock lock = new ReentrantLock();
    //AtomicReferenceFieldUpdater
    private AtomicReferenceFieldUpdater<User, String> updater = AtomicReferenceFieldUpdater.newUpdater(User.class, String.class, "name");

    //synchronized锁下更新User对象
    public void syncUser(){
        synchronized (this){
            final User user = this.syncUser;
            final User newUser = new User("冰河001", user.getAge());
            this.syncUser = newUser;
        }
    }

    //ReentrantLock锁下更新User对象
    public void lockUser(){
        lock.lock();
        try{
            final User user = this.lockUser;
            final User newUser = new User("冰河001", user.getAge());
            this.lockUser = newUser;
        }finally {
            lock.unlock();
        }
    }

    //AtomicReference原子类更新User对象
    public void atomicUser(){
        updater.compareAndSet(atomicUser, "冰河", "冰河001");
    }
}
