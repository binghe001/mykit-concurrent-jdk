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
package io.binghe.concurrent.chapter09;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 模拟不同任务提交订单、扣减库存、生成物流单
 */
public class CyclicBarrierTask implements Runnable{
    private String task;
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierTask(String task, CyclicBarrier cyclicBarrier) {
        this.task = task;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        IntStream.rangeClosed(1, 3).forEach((i) -> {
            try {
                Random random = new Random();
                Thread.sleep(random.nextInt(2000));
                System.out.println("任务" + i + "--完成" + task + "的操作");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //重置计数器的值
            cyclicBarrier.reset();
        });
    }
}
