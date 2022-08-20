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

import java.util.Date;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author binghe
 * @version 1.0.0
 * @description 线程的基本操作
 */
public class ThreadHandlerTest {

    public static void main1(String[] args){
        Thread thread = new Thread(() -> {
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
        },"binghe-thread-002");
        thread.start();
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
    }

    public static void main2(String[] args){
        Thread thread = new Thread(() -> {
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
        });
        thread.setName("binghe-thread-002");
        thread.start();
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
    }

    public static void main3(String[] args){
        Thread thread = new Thread(new ThreadGroup("binghe-thread-002-group"),() -> {
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
        });
        thread.start();
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
        System.out.println("子线程所在的线程组名称为===>>> " + thread.getThreadGroup().getName());
    }

    public static void main4(String[] args){
        Thread thread = new Thread(() -> {
            System.out.println("当前时间为===>>> " + new Date());
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前时间为===>>> " + new Date());
        });
        thread.start();
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
    }

    public static void main5(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("中断休眠中的线程会抛出异常，并清空中断标记，捕获异常后重新设置中断标记");
               Thread.currentThread().interrupt();
            }
        });
        thread.start();
        //保证子线程已经启动完成
        Thread.currentThread().sleep(500);
        System.out.println("在主线程中中断子线程");
        //中断子线程
        thread.interrupt();
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
    }


    public static void main6(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
            while (!Thread.currentThread().isInterrupted()){
            }
            System.out.println("子线程退出了while循环");
        });
        thread.start();
        //保证子线程已经启动完成
        Thread.currentThread().sleep(500);
        System.out.println("在主线程中中断子线程");
        //中断子线程
        thread.interrupt();
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
    }

    public static void main7(String[] args) throws InterruptedException {
        final Object obj = new Object();
        Thread thread = new Thread(() -> {
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
            System.out.println("子线程等待");
            synchronized (obj){
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("子线程被唤醒");
        });
        thread.start();
        //保证子线程已经启动完成
        Thread.currentThread().sleep(500);
        System.out.println("主线程通知子线程");
        synchronized (obj){
            obj.notify();
        }
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
    }

    public static void main8(String[] args) throws InterruptedException {
        final Object obj = new Object();
        Thread thread = new Thread(() -> {
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
            synchronized (obj){
                System.out.println("子线程挂起");
                Thread.currentThread().suspend();
            }
            System.out.println("子线程被唤醒");
        });
        thread.start();
        //保证子线程已经启动完成
        Thread.currentThread().sleep(500);
        System.out.println("主线程通知子线程继续执行");
        thread.resume();
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
    }

    private static int sum = 0;
    public static void main9(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
            IntStream.range(0, 1000).forEach((i) -> {sum += 1;});
        });
        thread.start();
//        //保证子线程已经启动完成
//        Thread.currentThread().sleep(500);
        thread.join();
        System.out.println("主线程获取到的结果为===>>> " + sum);
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
    }


    public static void main10(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
            while (true){
            }
        });
        System.out.println("启动子线程===>>> " + new Date());
        thread.start();
        //保证子线程已经启动完成
        Thread.currentThread().sleep(5000);
        System.out.println("强制退出子线程===>>> " + new Date());
        thread.stop();
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
    }

    public static void main11(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("binghe-thread-group-002");
        ThreadGroup subThreadGroup = new ThreadGroup(threadGroup,"binghe-sub-thread-group-002");
        Thread thread1 = new Thread(threadGroup, () -> {
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(subThreadGroup, () -> {
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
        //保证子线程已经启动完成
        Thread.currentThread().sleep(500);
        System.out.println("线程组中活跃的线程组数量为===>> " + threadGroup.activeGroupCount());
        System.out.println("线程组中活跃的线程数量为===>> " + threadGroup.activeCount());
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
    }

    public static void main12(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("binghe-thread-group-002");
        ThreadGroup subThreadGroup = new ThreadGroup(threadGroup,"binghe-sub-thread-group-002");
        Thread thread1 = new Thread(threadGroup, () -> {
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(subThreadGroup, () -> {
            System.out.println("子线程名称===>> " + Thread.currentThread().getName());
            //加入到subThreadGroup线程组中
            ThreadGroup thread2Group = new ThreadGroup("binghe-thread2-group-002");
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
        //保证子线程已经启动完成
        Thread.currentThread().sleep(500);
        System.out.println("threadGroup线程组中活跃的线程组数量为===>> " + threadGroup.activeGroupCount());
        System.out.println("threadGroup线程组中活跃的线程数量为===>> " + threadGroup.activeCount());
        System.out.println("subThreadGroup线程组中活跃的线程组数量为===>> " + subThreadGroup.activeGroupCount());
        System.out.println("主线程名称===>> " + Thread.currentThread().getName());
    }

    public static void main13(String[] args) {
        System.out.println(Thread.currentThread().getThreadGroup().getName());
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getName());
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getParent().getName());
    }

    public static void main14(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("binghe-thread-group-002");
        System.out.println("threadGroup线程组中活跃的线程组数量为===>> " + threadGroup.activeGroupCount());
        ThreadGroup subThreadGroup = new ThreadGroup(threadGroup,"binghe-sub-thread-group-002");
        System.out.println("threadGroup线程组中活跃的线程组数量为===>> " + threadGroup.activeGroupCount());
    }

    public static void main15(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup threadGroup = new ThreadGroup(mainGroup, "threadGroup");
        ThreadGroup subThreadGroup1 = new ThreadGroup(threadGroup,"subThreadGroup1");
        ThreadGroup subThreadGroup2 = new ThreadGroup(threadGroup,"subThreadGroup2");

        ThreadGroup[] threadGroups1 = new ThreadGroup[mainGroup.activeGroupCount()];
        //递归获取
        mainGroup.enumerate(threadGroups1, true);

        Stream.of(threadGroups1).forEach((tg) -> {
            if (tg != null){
                System.out.println("递归获取到的线程组===>> " + tg.getName());
            }
        });

        ThreadGroup[] threadGroups2 = new ThreadGroup[mainGroup.activeGroupCount()];
        //非递归获取
        mainGroup.enumerate(threadGroups2, false);

        Stream.of(threadGroups2).forEach((tg) -> {
            if (tg != null){
                System.out.println("非递归获取到的线程组===>> " + tg.getName());
            }
        });
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("threadGroup");
        System.out.println("创建并启动所有的线程===>>> " + new Date());
        IntStream.range(0, 5).forEach((i) -> {
            //将线程都添加到threadGroup线程组
            new Thread(threadGroup, () -> {
                while (!Thread.currentThread().isInterrupted()){

                }
                System.out.println("子线程" + Thread.currentThread().getName() + "被中断===>>> " + new Date());
            }).start();
        });
        //主线程休眠5秒
        Thread.currentThread().sleep(5000);
        System.out.println("主线程中断子线程");
        //使用线程组批量停止线程
        threadGroup.interrupt();
    }
}
