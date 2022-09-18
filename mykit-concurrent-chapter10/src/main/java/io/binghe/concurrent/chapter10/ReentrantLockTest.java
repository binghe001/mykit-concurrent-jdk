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
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description ReentrantLock对同一线程具有可重入性
 * 对多个线程具有排它性
 */
public class ReentrantLockTest {

    public static void main(String[] args){
        Lock lock = new ReentrantLock();
        IntStream.range(0, 2).forEach((i) -> {
            new Thread(new ReentrantLockTask(lock)).start();
        });
    }
}
