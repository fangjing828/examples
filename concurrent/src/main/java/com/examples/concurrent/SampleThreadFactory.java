package com.examples.concurrent;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class SampleThreadFactory implements ThreadFactory {
    private static final ThreadGroup threadGroup = new ThreadGroup("Sample");

    private final AtomicLong threadNumber = new AtomicLong(1);
    private final String namePrefix;
    private final boolean daemon;

    public static ThreadFactory create(String namePrefix, boolean daemon) {
        return new SampleThreadFactory(namePrefix, daemon);
    }

    public static boolean waitAllShutdown(int timeoutInMillis) {
        ThreadGroup group = threadGroup;
        String groupName = group.getName();
        Thread[] activeThreads = new Thread[group.activeCount()];
        group.enumerate(activeThreads);
        Set<Thread> actives = new HashSet<>(Arrays.asList(activeThreads));
//        log.info("Current ACTIVE thread count is: {}", actives.size());

        long expire = System.currentTimeMillis() + timeoutInMillis;
        while (System.currentTimeMillis() < expire) {
            actives = actives.stream().filter(t ->
                    t.isAlive() && !t.isInterrupted() && !t.isDaemon())
                    .collect(Collectors.toSet());
            if (actives.size() > 0) {
//                log.info("Active {} threads: {}", groupName, actives);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    // ignore
                }
            } else {
//                log.info("All {} threads are shutdown.", groupName);
                return true;
            }
        }

//        log.warn("Some {} threads are still alive but expire time has reached, alive threads: {}",
//                groupName, actives);
        return false;
    }

    private SampleThreadFactory(String namePrefix, boolean daemon) {
        this.namePrefix = namePrefix;
        this.daemon = daemon;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(threadGroup, runnable,//
                threadGroup.getName() + "-" + namePrefix + "-" + threadNumber.getAndIncrement());
        thread.setDaemon(daemon);
        // 不继承父线程的优先级，强制设置该线程组的线程优先级相同
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
//        thread.setUncaughtExceptionHandler((t, e) -> log.error("Executor: {} has uncaught exceptions",
//                t.getName(), e));
        return thread;
    }
}
