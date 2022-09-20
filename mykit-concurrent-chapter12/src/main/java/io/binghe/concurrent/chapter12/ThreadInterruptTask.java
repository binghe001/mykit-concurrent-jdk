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
package io.binghe.concurrent.chapter12;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description 线程中断案例
 */
public class ThreadInterruptTask implements Runnable {
    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        while (true){
            if (currentThread.isInterrupted()){
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("触发InterruptedException异常");
                //真正退出线程的关键
                currentThread.interrupt();
            }
        }
        System.out.println("线程被成功中断");
    }
}
