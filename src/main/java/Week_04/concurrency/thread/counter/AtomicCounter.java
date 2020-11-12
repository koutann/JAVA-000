/**
 * Copyright (C), 2015-2020, 京东
 * FileName: AutomicCounter
 * Author:   huangdan6
 * Date:     2020/11/12 下午4:22
 * Description: 计数器
 */
package Week_04.concurrency.thread.counter;


import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 计数器
 *
 * @author huangdan6
 * @date 2020/11/12 16:22
 * @since 1.0.0
 */
public class AtomicCounter {
    private AtomicInteger count = new AtomicInteger();

    int inc(){
        return count.getAndIncrement();
    }

    int getCount(){
        return count.get();
    }
}
