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
package io.binghe.concurrent.chapter13.example.future;

import io.binghe.concurrent.chapter13.example.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description CompletableFuture小案例
 * 模拟实现从淘宝、京东、当当对比某类图书的价格
 */
public class CompletableFutureCase {

    private static List<Shop> shopList = Arrays.asList(
            new Shop("淘宝"),
            new Shop("京东"),
            new Shop("当当")
    );

    public static List<String> getPrice(List<Shop> shopList, String goodsName){
        return shopList.stream().map((shop) ->
                CompletableFuture.supplyAsync(() -> String.format(goodsName + " 在 %s 的价格是 %.2f",
                        shop.getShopName(),
                        shop.getPrice(goodsName))))
                .collect(Collectors.toList())
                .stream()
                .map(future -> future.join())
                .collect(Collectors.toList());
    }

    public static void main(String[] args){
        List<String> list = CompletableFutureCase.getPrice(shopList, "深入理解高并发编程");
        list.forEach(System.out::println);
    }
}
