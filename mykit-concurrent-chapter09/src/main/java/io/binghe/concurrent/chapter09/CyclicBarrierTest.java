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

import java.util.concurrent.CyclicBarrier;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 模拟不同任务提交订单、扣减库存、生成物流单
 */
public class CyclicBarrierTest {

    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("当前提交订单、扣减库存、生成物流单完毕");
        });
        new Thread(new CyclicBarrierTask("提交订单", cyclicBarrier)).start();
        new Thread(new CyclicBarrierTask("扣减库存", cyclicBarrier)).start();
        new Thread(new CyclicBarrierTask("生成物流单", cyclicBarrier)).start();
    }
}
