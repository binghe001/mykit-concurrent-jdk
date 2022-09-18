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

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description Lock测试案例
 */
public class LockTask implements Runnable{
    private Lock lock;

    public LockTask(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "--线程获取到锁");
            System.out.println(Thread.currentThread().getName() + "--线程执行任务");
        }finally {
            System.out.println(Thread.currentThread().getName() + "--线程释放锁");
            lock.unlock();
        }
    }
}
