package io.binghe.concurrent.chapter13.future;
 
import java.util.concurrent.*;
 
/**
 * @author binghe
 * @version 1.0.0
 * @description 测试Future获取异步结果
 */
public class FutureTest {
 
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "测试Future获取异步结果";
            }
        });
        System.out.println(future.get());
        executorService.shutdown();
    }
}