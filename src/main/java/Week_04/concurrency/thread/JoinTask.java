package Week_04.concurrency.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JoinTask {
    public static void main(String [] a) {
        MyThread thread1 = new MyThread();
        thread1.setName("thread1");
        thread1.start();
        //
        for (int i =0 ;i < 20; i++){
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("当前线程名称：{},i={}", Thread.currentThread().getName(), i);
        }
    }

}
@Slf4j
class MyThread extends Thread{
    @Override
    public void run() {
        for(int i = 0; i < 20; i ++){
            log.info("当前线程名称：{},i={}", Thread.currentThread().getName(), i);
        }
    }
}
