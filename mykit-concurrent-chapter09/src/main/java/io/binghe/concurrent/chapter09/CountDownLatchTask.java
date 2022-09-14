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
import java.util.concurrent.CountDownLatch;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 模拟张三、李四、王五的线程
 */
public class CountDownLatchTask implements Runnable {
    private String name;
    private CountDownLatch countDownLatch;

    public CountDownLatchTask(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(2000));
            System.out.println(this.name + "--到达出发地");
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
