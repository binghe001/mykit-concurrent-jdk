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
package io.binghe.concurrent.chapter05;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.IntStream;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description ConcurrentSkipListSet案例测试
 */
public class ConcurrentSkipListSetTest {

    private static final ConcurrentSkipListSet<Integer> SET = new ConcurrentSkipListSet<>();

    static {
        //向ConcurrentSkipListSet中随机添加元素
        IntStream.rangeClosed(1, 10).forEach((i) -> {
            int randomNum = new Random().nextInt(10);
            //向ConcurrentSkipListSet中重复添加随机获取到的值
            //看ConcurrentSkipListSet中是否会存储重复的数据
            SET.add(randomNum);
            SET.add(randomNum);
        });
    }

    public static void main(String[] args){
        //升序遍历ConcurrentSkipListSet
        Iterator<Integer> iterator = SET.iterator();
        while (iterator.hasNext()){
            System.out.println("升序遍历输出的结果数据===>>> " + iterator.next());
        }
        System.out.println("===========================");
        //降序遍历ConcurrentSkipListSet
        iterator = SET.descendingIterator();
        while (iterator.hasNext()){
            System.out.println("降序遍历输出的结果数据===>>> " + iterator.next());
        }
    }
}
