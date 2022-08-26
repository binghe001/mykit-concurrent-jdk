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

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试Vector和ArrayList
 * 在单线程下分别添加1000000个元素、
 * 遍历1000000个元素和
 * 删除1000000个元素所使用的时间
 */
public class VectorVsArrayList {

    /**
     * 测试的元素数量
     */
    private static final int ELEMENT_COUNT = 1000000;

    /**
     * 测试的元素
     */
    private static final String ELEMENT_DATA = "binghe";
    /**
     * 参与测试的list集合
     */
    private static List<String> list = new ArrayList<>();
    /**
     * 参与测试的vector集合
     */
    private static Vector<String> vector = new Vector<>();

    /**
     * 测试ArrayList集合
     */
    public static void testArrayList(){
        //测试list
        System.out.println("==========测试ArrayList集合开始==============");

        //测试ArrayList集合添加1000000个元素耗时
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < ELEMENT_COUNT; i++){
            list.add(ELEMENT_DATA);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ArrayList集合添加1000000个元素耗时===>>> " + (endTime - startTime) + "ms");
        //测试ArrayList集合遍历1000000个元素耗时
        startTime = endTime;
        for (int i = 0; i < ELEMENT_COUNT; i++){
            list.get(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("ArrayList集合遍历1000000个元素耗时===>>> " + (endTime - startTime) + "ms");

        //测试ArrayList删除1000000个元素耗时
        startTime = endTime;
        for (int i = 0; i < ELEMENT_COUNT; i++){
            list.remove(ELEMENT_DATA);
        }
        endTime = System.currentTimeMillis();
        System.out.println("ArrayList集合删除1000000个元素耗时===>>> " + (endTime - startTime) + "ms");

        System.out.println("==========测试ArrayList集合结束==============");
    }
    /**
     * 测试Vector集合
     */
    public static void testVector(){
        //测试Vector
        System.out.println("==========测试Vector集合开始=================");
        //测试Vector集合添加1000000个元素耗时
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < ELEMENT_COUNT; i++){
            vector.add(ELEMENT_DATA);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Vector集合添加1000000个元素耗时===>>> " + (endTime - startTime) + "ms");

        //测试Vector集合遍历1000000个元素耗时
        startTime = endTime;
        for (int i = 0; i < ELEMENT_COUNT; i++){
            vector.get(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Vector集合遍历1000000个元素耗时===>>> " + (endTime - startTime) + "ms");

        //测试Vector删除遍历1000000个元素耗时
        startTime = endTime;
        for (int i = 0; i < ELEMENT_COUNT; i++){
            vector.remove(ELEMENT_DATA);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Vector集合删除1000000个元素耗时===>>> " + (endTime - startTime) + "ms");

        System.out.println("==========测试Vector集合结束=================");
    }

    public static void main(String[] args){
        testArrayList();
        testVector();
    }
}
