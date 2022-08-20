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
package io.binghe.consurrent.chapter02;

/**
 * @author binghe
 * @version 1.0.0
 * @description 实现Runnable接口创建线程
 */
public class RunnableTest{

    private static class MyRunnableTask implements Runnable{
        @Override
        public void run() {
            System.out.println("新创建的线程名称===>> " + Thread.currentThread().getName());
        }
    }

    /**
     * 以匿名类的方式创建线程
     */
    public Thread createThreadByRunnableAnonymousClass(){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新创建的线程名称===>> " + Thread.currentThread().getName());
            }
        });
    }


    /**
     * 以Lambda表达式的方式创建线程
     */
    public Thread createThreadByRunnableLambda(){
        return new Thread(() -> {
            System.out.println("新创建的线程名称===>> " + Thread.currentThread().getName());
        });
    }

    public static void main(String[] args) {
        new Thread(new MyRunnableTask()).start();
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
    }
}
