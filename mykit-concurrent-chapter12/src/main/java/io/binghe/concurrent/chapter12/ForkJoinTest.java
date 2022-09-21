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
package io.binghe.concurrent.chapter12;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description 使用fork/Join框架实现1~10000的累加和
 */
public class ForkJoinTest {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 10000;

    public static void main(String[] args) throws Exception {
        ForkJoinTaskComputer computer = new ForkJoinTaskComputer(MIN_COUNT, MAX_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> result = forkJoinPool.submit(computer);
        System.out.println("最终的计算结果为===>>> " + result.get());
    }
}
