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
package io.binghe.concurrent.threadpool;

import io.binghe.concurrent.policy.RejectHandler;
import io.binghe.concurrent.queue.CustomBlockingQueue;
import io.binghe.concurrent.queue.CustomQueue;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description 自定义线程池
 */
public class CustomThreadPoolExecutor implements CustomExecutor {

    //任务对列
    private CustomQueue<Runnable> workQueue;
    //线程集合
    private Set<WorkerThread> workerThreads = new HashSet<>();
    //线程核心数
    private int coreSize;
    //最大线程数
    private int maximumSize;
    //获取任务超时时间
    private long timeout;
    //获取任务超时时间的单位
    private TimeUnit timeUnit;
    //拒绝策略
    private RejectHandler rejectHandler;

    public CustomThreadPoolExecutor(int coreSize, int maximumSize, long timeout, TimeUnit timeUnit, CustomQueue<Runnable> workQueue) {
        if (coreSize <= 0 || maximumSize <= 0 || maximumSize < coreSize){
            throw new IllegalArgumentException();
        }
        this.coreSize = coreSize;
        this.maximumSize = maximumSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectHandler = new AbortPolicy();
        this.workQueue = workQueue;
    }

    public CustomThreadPoolExecutor(int coreSize, int maximumSize, long timeout, TimeUnit timeUnit, RejectHandler rejectHandler, CustomQueue<Runnable> workQueue) {
        if (coreSize <= 0 || maximumSize <= 0 || maximumSize < coreSize){
            throw new IllegalArgumentException();
        }
        this.coreSize = coreSize;
        this.maximumSize = maximumSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectHandler = rejectHandler;
        this.workQueue = workQueue;
    }

    @Override
    public void execute(Runnable task) {
        synchronized (workerThreads){
            //获取任务数，也就是线程池中目前存在的线程数
            int taskCount = workerThreads.size();
            //1.任务数没有超过coreSize，创建workerThread执行
            if (taskCount < coreSize){
                createNewThread(task);
            }else if (taskCount < maximumSize){   //2.任务数量大于等于coreSize，并且小于maximumSize
                //目前对列还有容量，向队列中添加元素
                if (workQueue.size() < workQueue.capcity()){  //2.1 队列不满，相队列中添加元素
                    workQueue.put(task);
                }else{              //2.2  队列已满，继续创建新的线程执行任务
                    createNewThread(task);
                }
            }else{     //3.队列已满，同时达到最大线程数，则执行拒绝策略
                rejectHandler.reject(task, this);
            }
        }
    }

    public CustomQueue<Runnable> getQueue() {
        return workQueue;
    }

    private void createNewThread(Runnable task){
        WorkerThread workerThread = new WorkerThread(task);
        workerThreads.add(workerThread);
        workerThread.start();
    }

    //用提交任务的线程执行任务
    public static class CallerRunsPolicy implements RejectHandler{
        @Override
        public void reject(Runnable r, CustomThreadPoolExecutor e) {
            r.run();
        }
    }

    //直接抛出异常
    public static class AbortPolicy implements RejectHandler{
        @Override
        public void reject(Runnable r, CustomThreadPoolExecutor e) {
            throw new RejectedExecutionException("Task " + r.toString() +
                    " rejected from " +
                    e.toString());
        }
    }

    //忽略任务
    public static class DiscardPolicy implements RejectHandler {
        @Override
        public void reject(Runnable r, CustomThreadPoolExecutor e) {

        }
    }

    //移除队列中存放最久的任务
    public static class DiscardOldestPolicy implements RejectHandler{
        @Override
        public void reject(Runnable r, CustomThreadPoolExecutor e) {
            e.getQueue().take();
            e.execute(r);
        }
    }


    class WorkerThread extends Thread{
        private Runnable task;

        public WorkerThread(Runnable task){
            this.task = task;
        }

        @Override
        public void run() {
            while (task != null || (task = workQueue.poll(timeout, timeUnit)) != null){
                try{
                    task.run();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    task = null;
                }
            }
            synchronized (workerThreads){
                workerThreads.remove(this);
            }
        }
    }
}
