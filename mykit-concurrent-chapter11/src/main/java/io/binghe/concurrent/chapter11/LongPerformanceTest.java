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
package io.binghe.concurrent.chapter11;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description 对比AtomicLong与LongAdder的性能
 */
@Warmup(iterations = 20)
@Measurement(iterations = 20)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Group)
public class LongPerformanceTest {

    @Benchmark
    @Group("atomicLong")
    @GroupThreads(50)
    public void atomicCount(LongPerformance performance){
        performance.atomicCount();
    }

    @Benchmark
    @Group("longAdder")
    @GroupThreads(50)
    public void adderCount(LongPerformance performance){
        performance.adderCount();
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(LongPerformanceTest.class.getSimpleName()).build();
        new Runner(options).run();
    }
}
