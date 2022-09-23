package io.binghe.concurrent.chapter16;
 
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
 
/**
 * @author binghe
 * @version 1.0.0
 * @description 测试Timer
 */
public class TimerTest {
 
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("测试Timer类===>>> " + new Date());
            }
        }, 1000, 1000);
        Thread.sleep(10000);
        timer.cancel();
    }
}