/**
 * Copyright (C), 2015-2020, 京东
 * FileName: AutomicMain
 * Author:   huangdan6
 * Date:     2020/11/12 下午4:24
 * Description: 计数器处理类
 */
package Week_04.concurrency.thread.counter;


import java.util.concurrent.*;

/**
 *
 * 计数器处理类
 *
 * @author huangdan6
 * @date 2020/11/12 16:24
 * @since 1.0.0
 */
public class AtomicMain {

    public static void main(String [] a){
        ExecutorService executorService =
                new ThreadPoolExecutor(8, 16, 3, TimeUnit.MINUTES, new LinkedBlockingQueue(100), new ThreadPoolExecutor.CallerRunsPolicy());
        AtomicCounter counter = new AtomicCounter();
        Future<Integer> future1 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                for (int i = 0; i < 10000000; i ++) {
                    counter.inc();
//                    System.out.println(Thread.currentThread().getName() + ":" + counter.getCount());
                }
                return counter.getCount();
            }
        });
        Future<Integer> future2 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                for (int i = 0; i < 10000000; i ++) {
                    counter.inc();
//                    System.out.println(Thread.currentThread().getName() + ":" + counter.getCount());
                }
                return counter.getCount();
            }
        });

        try {
            System.out.println("future1: " + future1.get());
            System.out.println("future2: " + future2.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("multiple threads: " + counter.getCount());
        executorService.shutdown();


    }
}
