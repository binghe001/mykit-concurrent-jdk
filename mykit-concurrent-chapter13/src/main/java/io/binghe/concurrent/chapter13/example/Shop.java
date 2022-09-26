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
package io.binghe.concurrent.chapter13.example;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description
 */
public class Shop implements Serializable {

    private static final long serialVersionUID = -2450864216810351893L;
    private String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public double getPrice(String goodsName){
//        try {
//            Random random = new Random();
//            //模拟获取价格的时间
//            Thread.sleep(random.nextInt(1000));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return ThreadLocalRandom.current().nextDouble() * 3 + (Math.abs(goodsName.hashCode()) % 100);
    }

    public String getShopName() {
        return shopName;
    }
}
