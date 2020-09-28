package com.springboott.ttdemo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 定义异步任务执行线程池
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 当一个任务通过execute(Runnable)方法欲添加到线程池时： https://www.cnblogs.com/xiaoshahai/p/11619729.html
     * <p>
     * 1  如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
     * 2  如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
     * 3  如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添加的任务。
     * 4  如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maximumPoolSize，那么通过 handler所指定的策略来处理此任务。
     * 也就是：处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、最大线程maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务。
     * 当线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。
     * <p>
     * handler有四个选择：
     * ThreadPoolExecutor.AbortPolicy() 抛出java.util.concurrent.RejectedExecutionException异常
     * ThreadPoolExecutor.CallerRunsPolicy 用于被拒绝任务的处理程序，它直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务。
     * ThreadPoolExecutor.DiscardOldestPolicy 丢弃任务队列中最旧任务
     * ThreadPoolExecutor.DiscardPolicy 用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。丢弃当前将要加入队列的任务本身
     */

    @Bean(name = "taskExecutorX")
    public Executor taskExecutorX() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);//核心线程数：线程池创建时候初始化的线程数
        executor.setMaxPoolSize(20);  //最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setQueueCapacity(200);//缓冲队列：用来缓冲执行任务的队列
        executor.setKeepAliveSeconds(10);//允许线程的空闲时间 秒：当超过了核心线程数之外的线程在空闲时间到达之后会被销毁
        executor.setThreadNamePrefix("taskExecutorX-");//线程池名的前缀：设置好了之后可以方便定位处理任务所在的线程池
        /**
         * 线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，
         * 当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；
         * 如果执行程序已关闭，则会丢弃该任务
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);//设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        /**
         * 设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
         * AwaitTerminationSeconds=xx（默认为0，此时立即停止），等待xx秒后强制停止
         */
        executor.setAwaitTerminationSeconds(10);
        return executor;
    }
}
