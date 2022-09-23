package io.binghe.concurrent.chapter16;
 
import java.util.Date;
import java.util.concurrent.*;
 
/**
 * @author binghe
 * @version 1.0.0
 * @description 测试ScheduledThreadPoolExecutor
 */
public class ScheduledThreadPoolExecutorTest {
    public static void main(String[] args) throws  InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("测试ScheduledThreadPoolExecutor===>>> " + new Date());
            }
        }, 1, 1, TimeUnit.SECONDS);
 
        //主线程休眠10秒
        Thread.sleep(10000);
 
        System.out.println("正在关闭线程池...");
        // 关闭线程池
        scheduledExecutorService.shutdown();
        boolean isClosed;
        // 等待线程池终止
        do {
            isClosed = scheduledExecutorService.awaitTermination(1, TimeUnit.DAYS);
            System.out.println("正在等待线程池中的任务执行完成");
        } while(!isClosed);
 
        System.out.println("所有线程执行结束，线程池关闭");
    }
}