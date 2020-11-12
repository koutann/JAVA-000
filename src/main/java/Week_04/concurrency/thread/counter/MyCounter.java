/**
 * Copyright (C), 2015-2020, 京东
 * FileName: MyCounter
 * Author:   huangdan6
 * Date:     2020/11/12 下午2:40
 * Description: 计数器
 */
package Week_04.concurrency.thread.counter;


import java.util.concurrent.*;

/**
 *
 * 计数器
 *
 * @author huangdan6
 * @date 2020/11/12 14:40
 * @since 1.0.0
 */
public class MyCounter {

    volatile int count = 0;
    public synchronized int add(){
        return count++;
    }
    int getCount(){
        return count;
    }
    //设置线程池的拒绝模式
    RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

    ExecutorService executor =
            new ThreadPoolExecutor(8, 16, 3, TimeUnit.MINUTES, new LinkedBlockingQueue(100), handler);

   public static void main(String[] a){
       int loop = 1000;
//       MyCounter myCounter1 = new MyCounter();
//       for (int i =0 ; i < loop; i ++) {
//           myCounter1.add();
//       }
//       System.out.println("multiple threads: " + myCounter1.getCount());
       MyCounter myCounter = new MyCounter();
       myCounter.executor.submit(() -> {
           for (int i =0 ; i < loop/2; i ++) {
               myCounter.add();
//               try {
//                   Thread.currentThread().join();
//               } catch (InterruptedException e) {
//                   e.printStackTrace();
//               }
               System.out.println(Thread.currentThread().getName() + ":" + myCounter.getCount());
           }
       });
       myCounter.executor.submit(() -> {
           for (int i =0 ; i < loop/2; i ++) {
               myCounter.add();
//               try {
//                   Thread.currentThread().join();
//               } catch (InterruptedException e) {
//                   e.printStackTrace();
//               }
               System.out.println(Thread.currentThread().getName() + ":" + myCounter.getCount());
           }
       });
       myCounter.executor.shutdown();
//       try {
//           Thread.sleep(100);
//       } catch (InterruptedException e) {
//           e.printStackTrace();
//       }
       System.out.println("multiple threads: " + myCounter.getCount());
   }
}
