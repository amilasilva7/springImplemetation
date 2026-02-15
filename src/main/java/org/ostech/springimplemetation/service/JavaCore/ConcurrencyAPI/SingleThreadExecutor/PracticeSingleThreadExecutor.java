package org.ostech.springimplemetation.service.JavaCore.ConcurrencyAPI.SingleThreadExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PracticeSingleThreadExecutor {
    public static void executeSingleThread() {
        System.out.println("Start of main Thread");
        Runnable task1 = () -> {
            System.out.println("Task 1 Simple Thread");
        };

        Runnable task2 = () -> {
            for (int i = 0; i < 5000; i++) {
                System.out.println("Task 2: i=" + i);
            }
        };

        ExecutorService executorService = null;

        try {
            executorService = Executors.newSingleThreadExecutor();
            executorService.execute(task1);
            executorService.execute(task2);
            executorService.execute(task1);
        } finally {
            if (executorService != null) {
                //stop yet to start Threads
                executorService.shutdown();

                //forceShutThreads
                //List<Runnable> stoppedThreads = executorService.shutdownNow();
            }
        }

        System.out.println("End of main Thread");
    }
}
