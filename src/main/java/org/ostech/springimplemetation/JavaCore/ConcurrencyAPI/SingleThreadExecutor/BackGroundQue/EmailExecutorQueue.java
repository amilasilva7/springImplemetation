package org.ostech.springimplemetation.JavaCore.ConcurrencyAPI.SingleThreadExecutor.BackGroundQue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class EmailExecutorQueue {

    @Bean(destroyMethod = "shutdown")
    public ExecutorService getEmailExecutorQueue() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService;
    }
}
