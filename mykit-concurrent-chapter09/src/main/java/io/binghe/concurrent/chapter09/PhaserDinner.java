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
 * @description 模拟聚餐的过程
 */
public class PhaserDinner extends Phaser {

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase){
            case 0:
                return allArrive();
            case 1:
                return allOrdered();
            case 2:
                return allDrink();
            default:
                return true;
        }
    }

    private boolean allDrink(){
        System.out.println("饮料点完了");
        return false;
    }

    /**
     * 模拟所有菜品已经点完
     */
    private boolean allOrdered(){
        System.out.println("菜品点完了");
        return false;
    }

    /**
     * 模拟所有人已经到齐
     */
    private boolean allArrive(){
        System.out.println("所有人都到齐了，开始点餐");
        return false;
    }
}
