package threadpool;

import java.time.Instant;
import java.util.concurrent.*;

/**
 * @program: danceOffer
 * @description: 线程池学习
 * @author: mobing_yin
 * @create: 2020-08-31 11:07
 **/

public class ThreadExecutorDemo {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {

        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("CurrentThread name:" + Thread.currentThread().getName() + "date：" + Instant.now());
            });
        }
        //终止线程池
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished all threads");
    }
    /**
     * 打印线程池的状态
     * @param threadPool 线程池对象
     */
    public static void printThreadPoolStatus(ThreadPoolExecutor threadPool) {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, createThreadFactory("print-images/thread-pool-status", false));
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("=========================");
            System.out.println("ThreadPool Size: [{}]"+ threadPool.getPoolSize());
            System.out.println("Active Threads: {}"+ threadPool.getActiveCount());
            System.out.println("Number of Tasks : {}"+ threadPool.getCompletedTaskCount());
            System.out.println("Number of Tasks in Queue: {}"+ threadPool.getQueue().size());
            System.out.println("=========================");
        }, 0, 1, TimeUnit.SECONDS);
    }
    /**
     * 线程池命名，有利于定位问题，加上定时打印线程池状态
     * guava的ThreadFactoryBuilder  & 自己创建的name线程工厂
     * */
    private static NamingThreadFactory createThreadFactory(String s, boolean b) {
        return new NamingThreadFactory(null, "name");
    }
}
