package org.ostech.springimplemetation.service.JavaCore.ConcurrencyAPI.ConcurrencyPatterns.BackGroundQueuePattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Configuration
public class EmailExecutorQueuePattern {

    @Bean(name = "emailExecutor", destroyMethod = "shutdown")
    public ExecutorService getEmailExecutorQueue() {
        /*Use Spring's CustomizableThreadFactory (Better for Spring)*/
        //ThreadFactory threadFactory = new CustomizableThreadFactory("email-worker-");

        /*With Virtual Threads (Modern Approach)*/
        ThreadFactory threadFactory = Thread.ofVirtual()
            .name("email-worker-", 0)
            .factory();
        return Executors.newSingleThreadExecutor(threadFactory);
    }
}
