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

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 模拟聚餐的过程
 */
public class PhaserTest {

    public static void main(String[] args){
        PhaserDinner phaserDinner = new PhaserDinner();
        new Thread(new PhaserTask("张三", phaserDinner)).start();
        phaserDinner.register();
        new Thread(new PhaserTask("李四", phaserDinner)).start();
        phaserDinner.register();
        new Thread(new PhaserTask("王五", phaserDinner)).start();
        phaserDinner.register();
    }
}
