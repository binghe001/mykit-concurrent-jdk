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
package io.binghe.concurrent.chapter04;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 测试CopyOnWriteArrayList迭代数据时
 * 向CopyOnWriteArrayList中添加数据的效果
 */
public class CopyOnWriteArrayListTest {

    private static final CopyOnWriteArrayList<Integer> LIST = new CopyOnWriteArrayList<>();
    static {
        //向CopyOnWriteArrayList中添加元素1~5
        IntStream.rangeClosed(1, 5).forEach((i) -> LIST.add(i));
    }

    public static void main(String[] args) throws InterruptedException {
        //在新线程中向CopyOnWriteArrayList中添加元素6~10
        Thread thread = new Thread(()->{
            IntStream.rangeClosed(6, 10).forEach((i) -> LIST.add(i));
        });
        //在启动线程之前获取CopyOnWriteArrayList的迭代器
        Iterator<Integer> iterator = LIST.iterator();
        //启动线程
        thread.start();
        //等待线程执行完毕
        thread.join();
        //迭代CopyOnWriteArrayList中的元素
        while (iterator.hasNext()){
            System.out.println("遍历出的数据为===>>> " + iterator.next());
        }
    }
}
