package io.binghe.concurrent.chapter02;
 
import java.util.concurrent.*;
 
/**
 * @author binghe
 * @version 1.0.0
 * @description 测试FutureTask获取异步结果
 */
public class FutureTaskTest {
 
    public static void main(String[] args)throws ExecutionException, InterruptedException{
        FutureTask<String> futureTask = new FutureTask<>(()-> {
                return "使用FutureTask配合Thread的方式创建线程";
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}