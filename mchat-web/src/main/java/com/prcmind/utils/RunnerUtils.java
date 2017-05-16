package com.prcmind.utils;



import static java.lang.String.format;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;


/**
 * ���߳�ִ�й���
 * 
 * @author adyliu (adyliu@sohu-inc.com)
 * @since 2011-5-18
 */
public class RunnerUtils {

    static class DefaultThreadFactory implements ThreadFactory {

        static final AtomicInteger poolNumber = new AtomicInteger(1);

        final ThreadGroup group;

        final String namePrefix;

        final AtomicInteger threadNumber = new AtomicInteger(1);

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "runnerutils-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY) t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    static class Monitor implements Runnable {

        final ThreadPoolExecutor executor;

        public Monitor(ThreadPoolExecutor executor) {
            this.executor = executor;
        }

        @Override
        public void run() {
            while (!executor.isShutdown()) {
                if (log.isDebugEnabled()) {
                    log.debug(format("activeCount/coreSize/maxSize/largetsSize|queueSize/reminning/completedCount %d/%d/%d/%d|%d/%d/%d",//
                            executor.getActiveCount(),//
                            executor.getCorePoolSize(),//
                            executor.getMaximumPoolSize(),//
                            executor.getLargestPoolSize(),//
                            //
                            executor.getQueue().size(),//
                            executor.getQueue().remainingCapacity(), executor.getCompletedTaskCount()//
                    ));
                }
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

    }

    private static volatile ExecutorService executor;

    private static final Logger log = Logger.getLogger(RunnerUtils.class);

    private static volatile ScheduledExecutorService scheduledExecutor;

    private static void initExecutor() {
        if (executor == null) {
            synchronized (RunnerUtils.class) {
                if (executor == null) {
                    executor = new ThreadPoolExecutor(11, 100, 1, TimeUnit.MINUTES, //
                            new ArrayBlockingQueue<Runnable>(10000),//
                            new DefaultThreadFactory());
                    //not use ScheduledExecutorService thread(waste resource)
                    executor.submit(new Monitor((ThreadPoolExecutor) executor));
                }
            }
        }
    }

    private static void initScheduledExecutor() {
        if (scheduledExecutor == null) {
            synchronized (RunnerUtils.class) {
                if (scheduledExecutor == null) {
                    scheduledExecutor = new ScheduledThreadPoolExecutor(10, new DefaultThreadFactory());
                }
            }
        }
    }

    /**
     * �����Ե���һ������
     * 
     * @param task ����
     * @param initialDelay ��ʼ��ʱ�����룩
     * @param delay ������ʱ�����룩
     */
    public static ScheduledFuture<?> schedule(Runnable task, long initialDelay, long delay) {
        initScheduledExecutor();
        return scheduledExecutor.scheduleWithFixedDelay(task, initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * �첽ִ��һ������
     * 
     * @param task ����
     * @return ������
     */
    public static <V> Future<V> submit(Callable<V> task) {
        initExecutor();
        return executor.submit(task);
    }

    /**
     * �첽ִ��һ������
     * 
     * @param task ����
     */
    public static Future<?> submit(Runnable task) {
        initExecutor();
        return executor.submit(task);
    }

    /**
     * shut down thread pools
     */
    public static void shutdown() {
        if (null != executor) {
            executor.shutdown();
        }
        if (null != scheduledExecutor) {
            scheduledExecutor.shutdown();
        }
    }

}
