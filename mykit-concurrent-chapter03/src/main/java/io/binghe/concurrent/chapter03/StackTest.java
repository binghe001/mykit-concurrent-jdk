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
package io.binghe.concurrent.chapter03;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * @author binghe
 * @version 1.0.0
 * @description 使用JMH对Stack进行基准测试
 */
@Fork(1)
@Measurement(iterations = 5)
@Warmup(iterations = 3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Threads(value = 5)
@State(Scope.Benchmark)
public class StackTest {
    /**
     * 测试的元素
     */
    private static final String ELEMENT_DATA = "binghe";

    private Stack<String> stack;

    @Setup
    public void setup(){
        this.stack = new Stack<>();
        stack.push(ELEMENT_DATA);
    }

    @Benchmark
    public void pushElement(){
        stack.push(ELEMENT_DATA);
    }

    @Benchmark
    public void peekElement(){
        stack.peek();
    }

    @Benchmark
    public void searchElement(){
        stack.search(ELEMENT_DATA);
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(StackTest.class.getSimpleName()).build();
        new Runner(opts).run();
    }
}
