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
package io.binghe.concurrent.chapter08;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description ConcurrentLinkedQueue和LinkedBlockingQueue性能对比
 */
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Group)
public class QueuePerformanceTest {

    //测试的数据元素
    private static final String DATA = "binghe";

    //参与测试的LinkedBlockingQueue
    private LinkedBlockingQueue<String> linkedBlockingQueue;
    //参与测试的ConcurrentLinkedQueue
    private ConcurrentLinkedQueue<String> concurrentLinkedQueue;

    @Setup(Level.Invocation)
    public void setUp(){
        linkedBlockingQueue = new LinkedBlockingQueue<>();
        concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    }

    @Benchmark
    @GroupThreads(5)
    @Group("linkedBlockingQueue")
    public void linkedBlockingQueueAdd(){
        linkedBlockingQueue.add(DATA);
    }

    @Benchmark
    @GroupThreads(5)
    @Group("linkedBlockingQueue")
    public void linkedBlockingQueuePoll(){
        linkedBlockingQueue.poll();
    }

    @Benchmark
    @GroupThreads(5)
    @Group("concurrentLinkedQueue")
    public void concurrentLinkedQueueAdd(){
        concurrentLinkedQueue.add(DATA);
    }

    @Benchmark
    @GroupThreads(5)
    @Group("concurrentLinkedQueue")
    public void concurrentLinkedQueuePoll(){
        concurrentLinkedQueue.poll();
    }

    public static void main(String[] args) throws RunnerException {
        final Options opt = new OptionsBuilder().include(QueuePerformanceTest.class.getName()).build();
        new Runner(opt).run();
    }
}
