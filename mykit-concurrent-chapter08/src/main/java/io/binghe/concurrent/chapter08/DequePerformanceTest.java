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

import java.util.concurrent.*;

/**
 * @author binghe (公众号：冰河技术)
 * @version 1.0.0
 * @description ConcurrentLinkedDeque和LinkedBlockingDeque性能对比
 */
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Group)
public class DequePerformanceTest {

    //测试的数据元素
    private static final String DATA = "binghe";

    //参与测试的LinkedBlockingDeque
    private LinkedBlockingDeque<String> linkedBlockingDeque;
    //参与测试的ConcurrentLinkedDeque
    private ConcurrentLinkedDeque<String> concurrentLinkedDeque;

    @Setup(Level.Invocation)
    public void setUp(){
        linkedBlockingDeque = new LinkedBlockingDeque<>();
        concurrentLinkedDeque = new ConcurrentLinkedDeque<>();
    }

    @Benchmark
    @GroupThreads(5)
    @Group("linkedBlockingDeque")
    public void linkedBlockingDequeAdd(){
        linkedBlockingDeque.add(DATA);
    }

    @Benchmark
    @GroupThreads(5)
    @Group("linkedBlockingDeque")
    public void linkedBlockingDequePoll(){
        linkedBlockingDeque.poll();
    }

    @Benchmark
    @GroupThreads(5)
    @Group("concurrentLinkedDeque")
    public void concurrentLinkedDequeAdd(){
        concurrentLinkedDeque.add(DATA);
    }

    @Benchmark
    @GroupThreads(5)
    @Group("concurrentLinkedDeque")
    public void concurrentLinkedDequePoll(){
        concurrentLinkedDeque.poll();
    }

    public static void main(String[] args) throws RunnerException {
        final Options opt = new OptionsBuilder().include(DequePerformanceTest.class.getName()).build();
        new Runner(opt).run();
    }
}
