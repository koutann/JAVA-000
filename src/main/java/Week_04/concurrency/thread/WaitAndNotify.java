package Week_04.concurrency.thread;

import lombok.extern.slf4j.Slf4j;

public class WaitAndNotify {

    public static void main(String[] a){
        MethodClass methodClass1 = new MethodClass();
        MethodClass methodClass2 = new MethodClass();
        Thread thread1 = new Thread(() -> {
            try {
                methodClass1.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1");
        Thread thread2 = new Thread(() -> {
            try {
                methodClass2.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread2");

        thread1.start();
        thread2.start();
    }


}

@Slf4j
class MethodClass{
    //定义生产量最大值
    public final static int MAX = 20;

    int productCount = 0;
    public synchronized void product() throws InterruptedException {
        while (true){
            log.info("product-线程名称：{},productCount={}", Thread.currentThread().getName(), productCount);
            if (productCount >= MAX) {
                log.info("货仓已满，不在生产");
                wait();
            } else {
                Thread.sleep(10);
                productCount ++;
            }
            notifyAll();
        }

    }

    public synchronized void consume() throws InterruptedException {

        while (true){
            log.info("consume-线程名称：{},productCount={}", Thread.currentThread().getName(), productCount);
            if (productCount <= 0) {
                log.info("货仓马上空了，唤醒生产！");
                wait();
            } else {
                Thread.sleep(10);
                productCount --;
            }
            notifyAll();
        }

    }

}
