package io.binghe.concurrent.chapter13.call;
 
/**
 * @author binghe
 * @version 1.0.0
 * @description 回调函数的实现类
 */
public class TaskHandler implements TaskCallable<TaskResult> {
    @Override
    public TaskResult callable(TaskResult taskResult) {
        //拿到结果数据后进一步处理
        System.out.println(taskResult.toString());
            return taskResult;
        }
}