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

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 定时任务类
 */
public class TimerTask implements Delayed, Runnable {
    //延迟时长，出队时判断数据在队列中
    //是否达到了interval时长
    //如果未达到interval时长，则继续等待
    //如果已达到interval时长，则出队
    private long interval;
    //数据放入队列的时间
    //结合系统当前时间判断元素
    //是否已经达到延迟时间
    private long queueTime;
    //延迟队列
    private DelayQueue<TimerTask> delayQueue;
    //执行的任务
    private Runnable task;

    public TimerTask(DelayQueue<TimerTask> delayQueue, Runnable task,  long interval) {
        this.interval = interval;
        this.delayQueue = delayQueue;
        this.task = task;
    }

    @Override
    public void run() {
        //执行具体的任务
        task.run();
        //每次执行任务时，更新time时间
        queueTime = System.currentTimeMillis();
        delayQueue.put(this);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((this.queueTime + this.interval) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(this.queueTime - ((TimerTask)o).getQueueTime());
    }

    public long getQueueTime() {
        return queueTime;
    }
}
