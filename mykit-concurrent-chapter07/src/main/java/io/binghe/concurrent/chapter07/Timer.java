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
package io.binghe.concurrent.chapter07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 定时任务
 */
public class Timer {
    private static final Logger logger = LoggerFactory.getLogger(Timer.class);
    //延迟队列
    private static DelayQueue<TimerTask> delayQueue = new DelayQueue<>();
    //创建线程池，执行任务
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
    //定时执行任务的周期频率
    private static final Long INTERVAL = 1000L;

    public void schedule(Runnable task, long interval){
        threadPool.execute(new TimerTask(delayQueue, task, interval));
        threadPool.execute(() -> {
            while (true){
                try {
                    TimerTask timerTask = delayQueue.take();
                    threadPool.execute(timerTask);
                } catch (InterruptedException e) {
                    logger.error("执行定时任务抛出了异常");
                }
            }
        });
    }

    public static void main(String[] args){
        new Timer().schedule(()-> {
            System.out.println(Thread.currentThread().getName() + " 线程执行任务的当前时间为：" + new Date());
        },INTERVAL);
    }
}
