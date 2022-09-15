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

import java.util.concurrent.Phaser;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 模拟聚餐的任务
 */
public class PhaserTask implements Runnable {
    private String name;
    private Phaser phaser;

    public PhaserTask(String name, Phaser phaser) {
        this.name = name;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        //到达聚餐地点
        System.out.println(name + "--到达聚餐地点");
        phaser.arriveAndAwaitAdvance();

        //点菜品
        System.out.println(name + "--点了一份香辣牛肉");
        phaser.arriveAndAwaitAdvance();

        //点饮料
        System.out.println(name + "--点了一份常温豆奶");
        phaser.arriveAndAwaitAdvance();
    }
}
