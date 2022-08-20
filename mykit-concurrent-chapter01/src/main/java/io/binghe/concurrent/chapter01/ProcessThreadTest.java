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
package io.binghe.concurrent.chapter01;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试进程与线程
 */
public class ProcessThreadTest {

    public static void main(String[] args){
        new Thread(() -> {
            while (true){

            }
        },"thread-binghe-001").start();
        System.out.println("程序启动成功...");
    }
}
