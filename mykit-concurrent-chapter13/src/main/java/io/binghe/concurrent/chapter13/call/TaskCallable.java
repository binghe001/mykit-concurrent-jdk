package io.binghe.concurrent.chapter13.call;
 
/**
 * @author binghe
 * @version 1.0.0
 * @description 定义回调接口
 */
public interface TaskCallable<T> {
    T callable(T t);
}