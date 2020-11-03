package Week_03.gateway.outbound;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
    //线程所属的线程组
    private final ThreadGroup threadGroup;
    // 记录当前线程是线程组中的第几个线程
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    //线程名称
    private final String namePrefix;
    //是否是守护线程
    private final boolean daemon;

    public NamedThreadFactory(String namePrefix, boolean daemon){
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        threadGroup = (s != null)? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix;
    }

    public NamedThreadFactory (String namePrefix) {
        this(namePrefix, false);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(threadGroup, r, namePrefix + "-thread-" + threadNumber.getAndIncrement(), 0);
        thread.setDaemon(this.daemon);
        return thread;
    }
}
