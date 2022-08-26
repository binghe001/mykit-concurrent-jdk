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
package io.binghe.concurrent.chapter02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author binghe
 * @version 1.0.0
 * @description 实现Callable接口创建线程
 */
public class CallableTest {

    private static class MyCallbleTask implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("新创建的线程名称===>> " + Thread.currentThread().getName());
            return Thread.currentThread().getName();
        }
    }

    /**
     * 以匿名类的方式创建线程
     */
    public FutureTask createThreadByCallableAnonymousClass() {
        return new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("新创建的线程名称===>> " + Thread.currentThread().getName());
                return Thread.currentThread().getName();
            }
        });
    }
    /**
     * 以Lambda表达式的方式创建线程
     */
    public FutureTask createThreadByCallableLambda() {
        return new FutureTask<>(() -> {
            System.out.println("新创建的线程名称===>> " + Thread.currentThread().getName());
            return Thread.currentThread().getName();
        });
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyCallbleTask());
        new Thread(futureTask).start();
        System.out.println("从子线程中获取到的数据为===>> " + futureTask.get());
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
    }
}
