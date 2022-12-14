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
package io.binghe.concurrent.policy;


import io.binghe.concurrent.queue.CustomQueue;
import io.binghe.concurrent.threadpool.CustomThreadPoolExecutor;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description 拒绝策略接口
 */
@FunctionalInterface
public interface RejectHandler {

    /**
     * 拒绝任务回调接口
     */
    void reject(Runnable r, CustomThreadPoolExecutor executor);
}
