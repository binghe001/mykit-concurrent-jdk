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
package io.binghe.concurrent.chapter02;

import java.util.concurrent.*;

/**
 * @author binghe
 * @version 1.0.0
 * @description 通过ThreadPoolExecutor类创建线程
 */
public class ThreadPoolExecutorTest {

    private static ThreadPoolExecutor threadPool;

    static {
        threadPool = new ThreadPoolExecutor(3, 3, 30,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
    }

    public static void main1(String[] args) {
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
        threadPool.submit(() -> {
            System.out.println("新创建的线程名称===>> " + Thread.currentThread().getName());
        });
    }
    public static void main2(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
        Future<String> future = threadPool.submit(() -> {
            System.out.println("新创建的线程名称===>> " + Thread.currentThread().getName());
            return Thread.currentThread().getName();
        });
        System.out.println("从子线程中获取到的数据为===>> " + future.get());
    }

    public static void main(String[] args){
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
        threadPool.execute(() -> {
            System.out.println("新创建的线程名称===>> " + Thread.currentThread().getName());
        });
    }
}
