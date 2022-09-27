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
package io.binghe.concurrent.test;

import io.binghe.concurrent.queue.CustomBlockingQueue;
import io.binghe.concurrent.threadpool.CustomThreadPoolExecutor;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description 测试自定义线程池
 */
public class CustomThreadPoolExecutorTest {
    public static void main(String[] args){
        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new CustomBlockingQueue<>(8));
        IntStream.rangeClosed(1, 100).forEach((i) -> {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "===>>> 正在执行任务");
            });
        });
    }
}
