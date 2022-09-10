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

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description 生产者与消费者案例
 */
public class ProducerAndConsumerTest {

    private static final Logger logger = LoggerFactory.getLogger(ProducerAndConsumerTest.class);
    //定义一个生产者线程池
    private static ThreadPoolExecutor producerThreadPool = new ThreadPoolExecutor(20, 20, 500, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000));
    //定义一个消费者线程池
    private static ThreadPoolExecutor consumerThreadPool = new ThreadPoolExecutor(20, 20, 500, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000));

    //定义一个阻塞队列
    private static BlockingQueue<Long> queue = new ArrayBlockingQueue<>(5);

    //发送数据的计数器，生产者每向队列中添加一个数据，值就+1
    private static AtomicLong atomicLong = new AtomicLong(0);

    //生产者生产数据
    private static void produceData(){
        //向线程池中提交10个任务
        IntStream.range(0, 10).forEach((i) -> {
            producerThreadPool.execute(() -> {
                try {
                    Thread.sleep(200);
                    //获取线程的名称
                    String threadName = Thread.currentThread().getName();
                    //生产的数据
                    Long data = atomicLong.incrementAndGet();
                    //将数据写入队列
                    queue.put(data);
                    System.out.println("生产者线程：" + threadName + " 向队列添加的数据为： " +  data);
                } catch (InterruptedException e) {
                    logger.error("生产者生产数据异常: {}" ,e);
                }
            });
        });
    }

    //消费者消费数据
    private static void consumerData(){
        //向线程池中提交10个任务
        IntStream.range(0, 10).forEach((i) -> {
            consumerThreadPool.execute(()->{
                try {
                    Thread.sleep(200);
                    //获取线程的名称
                    String threadName = Thread.currentThread().getName();
                    //从队列中获取数据
                    Long data = queue.take();
                    System.out.println("消费者线程：" + threadName + " 获取到的数据为：" + data);
                } catch (InterruptedException e) {
                    logger.error("消费者消费数据异常: {}" ,e);
                }
            });
        });
    }

    public static void main(String[] args){
        produceData();
        consumerData();
        producerThreadPool.shutdown();
        consumerThreadPool.shutdown();
    }
}
