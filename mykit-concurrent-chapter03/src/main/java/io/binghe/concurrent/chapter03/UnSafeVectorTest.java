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

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author binghe
 * @version 1.0.0
 * @description 以Vector为例测试竞态条件问题
 */
public class UnSafeVectorTest {

    /**
     * 测试的元素
     */
    private static final String ELEMENT_DATA = "binghe";

    // 同时并发执行的线程数
    public static final int THREAD_COUNT = 200;
    //执行的总次数
    public static final int TOTAL_COUNT = 5000;

    private static Vector<String> vector = new Vector<>();

    public static void addIfEmpty(){
        if (vector.isEmpty()){
            vector.add(ELEMENT_DATA);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //创建一个线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        final CountDownLatch countDownLatch = new CountDownLatch(TOTAL_COUNT);
        for (int i = 0; i < TOTAL_COUNT; i++) {
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();//是否允许被执行
                    addIfEmpty();
                    semaphore.release();
                } catch (Exception e) {
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("vector的最终大小为====>>> " + vector.size());
    }
}
