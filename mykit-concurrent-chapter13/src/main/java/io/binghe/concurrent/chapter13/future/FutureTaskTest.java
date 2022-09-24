package io.binghe.concurrent.chapter13.future;
 
import java.util.concurrent.*;
 
/**
 * @author binghe
 * @version 1.0.0
 * @description 测试FutureTask获取异步结果
 */
public class FutureTaskTest {
 
    public static void main(String[] args)throws ExecutionException, InterruptedException{
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "测试FutureTask获取异步结果";
            }
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}