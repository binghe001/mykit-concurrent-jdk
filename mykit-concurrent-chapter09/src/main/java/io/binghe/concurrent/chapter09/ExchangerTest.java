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

import java.util.concurrent.Exchanger;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description Exchanger模拟张三到超市买商品
 */
public class ExchangerTest {

    public static void main(String[] args){
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                String exchangeResult = exchanger.exchange("付款信息");
                Thread.sleep(500);
                System.out.println("张三收到收银员递过来的--" + exchangeResult);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                String exchangeResult = exchanger.exchange("商品");
                System.out.println("收银员收到张三的--" + exchangeResult);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
