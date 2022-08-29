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
package io.binghe.concurrent.chapter03;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试HashMap
 */
public class HashMapTest {
    // 同时并发执行的线程数
    public static final int THREAD_COUNT = 200;
    //执行的总次数
    public static final int TOTAL_COUNT = 5000;
    // HashMap
//    private static Map<Integer,Integer> map = new HashMap<>();
    private static Map<Integer,Integer> map = Collections.synchronizedMap(new HashMap<>());

    public static void main(String[] args) throws InterruptedException {
        //创建一个线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        final CountDownLatch countDownLatch = new CountDownLatch(TOTAL_COUNT);
        for (int i = 0; i < TOTAL_COUNT; i++) {
            final int count = i;
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();//是否允许被执行
                    map.put(count, count);
                    semaphore.release();
                } catch (Exception e) {
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("map的最终大小为====>>> " + map.size());
    }
}
