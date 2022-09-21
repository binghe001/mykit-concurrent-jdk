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
package io.binghe.concurrent.chapter12;

import java.util.concurrent.RecursiveTask;

/**
 * @author binghe(公众号：冰河技术)
 * @version 1.0.0
 * @description 使用fork/Join框架实现1~10000的累加和
 */
public class ForkJoinTaskComputer extends RecursiveTask<Integer> {
    //任务拆分的最小粒度
    private static final int MIN_COUNT = 2;
    //开始数字
    private int startNum;
    //结束数字
    private int endNum;

    public ForkJoinTaskComputer(int startNum, int endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
    }

    @Override
    protected Integer compute() {
        //计算结果
        int sum = 0;
        int count = endNum - startNum;
        //达到可计算的范围
        if (count <= MIN_COUNT){
            for (int i = startNum; i <= endNum; i++){
                sum += i;
            }
        }else{
            //找到中间值
            int middleCount = (startNum + endNum) / 2;
            //生成子任务
            ForkJoinTaskComputer leftComputer = new ForkJoinTaskComputer(startNum, middleCount);
            //生成子任务
            ForkJoinTaskComputer rightComputer = new ForkJoinTaskComputer(middleCount + 1, endNum);

            //执行子任务
            leftComputer.fork();
            rightComputer.fork();

            //合并结果
            Integer leftResult = leftComputer.join();
            Integer rightResult = rightComputer.join();

            sum += (leftResult + rightResult);

        }
        return sum;
    }
}
