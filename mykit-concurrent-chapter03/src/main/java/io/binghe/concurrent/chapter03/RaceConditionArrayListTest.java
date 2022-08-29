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
package io.binghe.concurrent.chapter03;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description 以List为例测试竞态条件问题
 */
public class RaceConditionArrayListTest {

    /**
     * 测试的元素
     */
    private static final String ELEMENT_DATA = "binghe";

    private static List<String> list = Collections.synchronizedList(new ArrayList<>());

    static {
        IntStream.range(0, 10).forEach((i) -> {
            list.add(ELEMENT_DATA);
        });
    }

    /**
     * 遍历容器
     */
    private static void ergodicArrayList(){
        synchronized (list){
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()){
                printElement(iterator.next());
            }
        }
    }

    private static void printElement(String str) {
        System.out.println("输出的元素信息====>>> " + str);
    }

    private static void removeElement(){
        list.remove(ELEMENT_DATA);
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{ ergodicArrayList();}, "read-thread").start();
        new Thread(() -> {removeElement();}, "write-thread").start();
    }
}
