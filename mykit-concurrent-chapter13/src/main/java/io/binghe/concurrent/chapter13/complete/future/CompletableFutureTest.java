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
package io.binghe.concurrent.chapter13.complete.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description CompletableFuture初始化
 */
public class CompletableFutureTest {

    //无返回结果的初始化
    public void runInit(){
        CompletableFuture.runAsync(() -> {
            System.out.println("无返回结果的异步任务");
        });
    }

    //有返回结果的初始化
    public String supplyInit() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "binghe";
        });
        return future.get();
    }


    public void serialization() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        }).thenApply((s) -> s + " binghe")
                .thenApply((s) -> s.replace("binghe", "binghe001"));
        System.out.println(future.get());
    }

    public void parallelization() throws Exception{
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "Hello binghe001";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "Hello binghe002";
        });
        CompletableFuture<Object> future = CompletableFuture.anyOf(future1, future2);
        System.out.println(future.get());
    }


    public void andTask() throws Exception {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int count = new Random().nextInt(10);
            System.out.println("任务1结果：" + count);
            return count;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int count = new Random().nextInt(10);
            System.out.println("任务2结果：" + count);
            return count;
        });
        CompletableFuture<Integer> future = future1
            .thenCombine(future2, (x ,y) -> {
                return x + y;
            });
        System.out.println("组合后结果：" + future.get());
    }

    public void orTask() throws Exception {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()-> {
            int time = new Random().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结果:" + time);
            return time;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int time = new Random().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2结果:" + time);
            return time;
        });
        CompletableFuture<Integer> future = future1.applyToEither(future2, (t) -> {
            System.out.println("最快返回的结果为结果：" + t);
            return t * 2;
        });
        System.out.println("最终的结果为===>>> " + future.get());
    }


    public void resultTask(){
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() ->  3 * 5)
                .thenApply((r) -> r / 0)
                .thenApply((r) -> r * 5)
                .exceptionally((e) -> {
                    System.out.println("抛出的异常信息为：" + e.getMessage());
                    return 0;
                })
                .handle((t, u) -> {
                    System.out.println("结果数据为：" + t);
                    return t;
                });
        System.out.println(future.join());
    }

    public static void main(String[] args) throws Exception {
        CompletableFutureTest completableFutureTest = new CompletableFutureTest();
        completableFutureTest.resultTask();
    }
}
