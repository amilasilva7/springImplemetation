package org.ostech.springimplemetation.JavaCore.ConcurrencyAPI.SingleThreadExecutor.SequentialProcessingGuarantee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Configuration
public class OrderExecutorQueue {

    @Bean(name = "orderExecutor", destroyMethod = "shutdown")
    public ExecutorService getOrderExecutorQueue() {
        ThreadFactory threadFactory = Thread.ofVirtual()
            .name("order-worker-", 0)
            .factory();
        return Executors.newSingleThreadExecutor(threadFactory);
    }
}
