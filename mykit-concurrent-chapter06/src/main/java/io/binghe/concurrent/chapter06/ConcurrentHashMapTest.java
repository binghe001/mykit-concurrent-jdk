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
package io.binghe.concurrent.chapter06;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description ConcurrentHashMap测试案例
 */
public class ConcurrentHashMapTest {

    //map中的key
    private static final String KEY = "binghe";
    // 同时并发执行的线程数
    public static final int THREAD_COUNT = 100;
    //执行的总次数
    public static final int TOTAL_COUNT = 100000;


    public static void main(String[] args) throws InterruptedException {
        Map<String, AtomicInteger> map = new ConcurrentHashMap<>();
        //创建一个线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        final CountDownLatch countDownLatch = new CountDownLatch(TOTAL_COUNT);
        for (int i = 0; i < TOTAL_COUNT; i++) {
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();//是否允许被执行
                    AtomicInteger value = map.get(KEY);;
                    if (value == null){
                        AtomicInteger zeroValue = new AtomicInteger(0);
                        value = map.putIfAbsent(KEY, zeroValue);
                        if (value == null){
                            value = zeroValue;
                        }
                    }
                    value.incrementAndGet();
                    semaphore.release();
                } catch (Exception e) {
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("执行完毕输出的结果信息为====>>> " + map.get(KEY).get());
    }
}
