package io.binghe.concurrent.chapter13.call;
 
/**
 * @author binghe
 * @version 1.0.0
 * @description 测试回调
 */
public class TaskCallableTest {
    public static void main(String[] args){
        TaskCallable<TaskResult> taskCallable = new TaskHandler();
        TaskExecutor taskExecutor = new TaskExecutor(taskCallable, "测试回调任务");
        new Thread(taskExecutor).start();
    }
}