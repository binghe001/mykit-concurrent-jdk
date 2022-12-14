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
package io.binghe.concurrent.queue;

import java.util.concurrent.TimeUnit;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description 队列接口
 */
public interface CustomQueue<T> {

    /**
     * 超时获取并移除队列数据
     */
    T poll(long timeout, TimeUnit timeUnit);

    /**
     * 阻塞获取并移除队列数据
     */
    T take();

    /**
     * 向队列中添加元素
     */
    void put(T task);


    /**
     * 向队列中超时添加元素
     */
    boolean offer(T task, long timeout, TimeUnit timeUnit);

    /**
     * 返回队列中元素的个数
     */
    int size();

    /**
     * 对列容量
     */
    int capcity();
}
