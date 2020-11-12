package Week_04.concurrency.thread;

public class RunnableThread {
    public static void main(String[] a){

        Runnable task = new Runnable(){
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
        Thread thread = new Thread(task);
        thread.setDaemon(false); // 守护线程，为前线线程提供服务，jvm检测到所有的前线线程都结束后，自动销毁守护线程
        thread.setName("Daemon-thread");
        thread.start();

//        try {
//            Thread.sleep(100);
//            System.out.println("主线程名称："+ Thread.currentThread().getName());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
