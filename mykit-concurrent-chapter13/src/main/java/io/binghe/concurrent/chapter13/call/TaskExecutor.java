package io.binghe.concurrent.chapter13.call;
 
/**
 * @author binghe
 * @version 1.0.0
 * @description 任务执行类
 */
public class TaskExecutor implements Runnable{
    private TaskCallable<TaskResult> taskCallable;
    private String taskParameter;
 
    public TaskExecutor(TaskCallable<TaskResult> taskCallable, String taskParameter){
        this.taskCallable = taskCallable;
        this.taskParameter = taskParameter;
    }
 
    @Override
    public void run() {
        //一系列业务逻辑,将结果数据封装成TaskResult对象并返回
        TaskResult result = new TaskResult();
        result.setTaskStatus(1);
        result.setTaskMessage(this.taskParameter);
        result.setTaskResult("异步回调成功");
        taskCallable.callable(result);
    }
}