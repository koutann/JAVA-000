package Week_04.concurrency.thread;

public class ThreadTest {
    public static void main(String [] a) {
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程名称："+ Thread.currentThread().getName());
            }
        };
        t.setName("thread-test");
        t.start();
        System.out.println("子线程名称："+ Thread.currentThread().getName());
    }
}
