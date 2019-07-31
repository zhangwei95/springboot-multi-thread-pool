package com.zw.demo.async;

import com.zw.demo.config.MicroServiceProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Resource
    MicroServiceProperty microServiceProperty;

    /**
     * handler ： 线程池对拒绝任务的处理策略。在 ThreadPoolExecutor 里面定义了 4 种 handler 策略，分别是
     *
     * 1. CallerRunsPolicy ：这个策略重试添加当前的任务，他会自动重复调用 execute() 方法，直到成功。
     *
     * 2. AbortPolicy ：对拒绝任务抛弃处理，并且抛出异常。
     *
     * 3. DiscardPolicy ：对拒绝任务直接无声抛弃，没有异常信息。
     *
     * 4. DiscardOldestPolicy ：对拒绝任务不抛弃，而是抛弃队列里面等待最久的一个线程，然后把拒绝任务加到队列。
     * @return
     */
    @Bean
    public Executor mySimpleAsync() {
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        executor.setCorePoolSize(microServiceProperty.getCorePoolSize());
        executor.setMaxPoolSize(microServiceProperty.getMaxPoolSize());
        executor.setQueueCapacity(microServiceProperty.getQueueCapacity());
        executor.setThreadNamePrefix("MySimpleExecutor-");
        executor.initialize();
        // 设置线程池的拒绝策略为DiscardOldestPolicy 丢弃任务
        // DiscardOldest：丢弃任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        return executor;
    }

    @Bean
    public Executor myAsync() {
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        executor.setCorePoolSize(microServiceProperty.getCorePoolSize());
        executor.setMaxPoolSize(microServiceProperty.getMaxPoolSize());
        executor.setQueueCapacity(microServiceProperty.getQueueCapacity());
        executor.setThreadNamePrefix("MyExecutor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
