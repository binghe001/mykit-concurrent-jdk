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
package io.binghe.concurrent.chapter13.example.service;

import io.binghe.concurrent.chapter13.example.Shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description CompletionService小案例
 * 使用Forking Cluster模式模拟实现从淘宝、京东、当当获取某类商品价格，只要有一个平台返回结果，则服务返回结果
 */
public class CompletionServiceCase {

    private static List<Shop> shopList = Arrays.asList(
            new Shop("淘宝"),
            new Shop("京东"),
            new Shop("当当")
    );

    public static String getPrice(List<Shop> shopList, String goodsName) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50));
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
        List<Future<String>> futureList = new ArrayList<>(3);
        shopList.stream().forEach((shop) -> {
            futureList.add(completionService.submit(() -> String.format(goodsName + " 在 %s 的价格是 %.2f",
                    shop.getShopName(),
                    shop.getPrice(goodsName))));
        });
        //获取执行最快的任务
        String priceInfo = null;
        try{
            for (int i = 0; i < shopList.size(); i ++){
                priceInfo = completionService.take().get();
                if (priceInfo != null){
                    break;
                }
            }
        }finally {
            //取消所有任务的执行
            futureList.stream().forEach((future) -> {
                future.cancel(true);
            });
            executor.shutdown();
        }
        return priceInfo;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(CompletionServiceCase.getPrice(shopList, "深入理解高并发编程"));
    }
}
