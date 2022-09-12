package io.binghe.concurrent.chapter08;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue内存泄露示例
 * 代码来自：https://bugs.java.com/bugdatabase/view_bug.do?bug_id=8137185
 */
public class CLQBug {
    public static void main(String[] args) {
        Queue<Object> queue = new ConcurrentLinkedQueue<>();
        queue.offer(new Object());

        Object item = new Object();

        long iterations = 0;
        try {
            while (true)
            {
                ++iterations;
                queue.offer(item);
                queue.remove(item);
            }
        } catch (OutOfMemoryError e){
            queue = null;
            System.err.println("iterations: " + iterations);
            throw e;
        }
    }
}