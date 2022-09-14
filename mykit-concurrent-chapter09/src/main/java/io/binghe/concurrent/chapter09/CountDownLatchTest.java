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

import java.util.concurrent.CountDownLatch;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 模拟导游等待张三、李四和王五一起去旅游
 */
public class CountDownLatchTest {

    public static void main(String[] args){
        //创建一个计数器为3的CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(3);
        //导游线程
        new Thread(()->{
            try {
                System.out.println("导游--等待张三、李四和王五到达出发地");
                countDownLatch.await();
                System.out.println("张三、李四和王五都已经到达出发地，一起跟随导游出发去目的地旅游");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "daoyou-thread").start();
        //张三线程
        new Thread(new CountDownLatchTask("张三", countDownLatch)).start();
        new Thread(new CountDownLatchTask("李四", countDownLatch)).start();
        new Thread(new CountDownLatchTask("王五", countDownLatch)).start();
    }
}
