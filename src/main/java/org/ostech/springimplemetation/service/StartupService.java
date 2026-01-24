package org.ostech.springimplemetation.service;

import jakarta.transaction.Transactional;
import org.ostech.springimplemetation.entity.Account;
import org.ostech.springimplemetation.repository.AccountRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class StartupService {
    AccountRepository accountRepository;

    StartupService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationStart() {
        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.submit(() -> {
                Account account = new Account();
                saveParallel(account);
            });
        }
        long endTime = System.nanoTime();
        System.out.println("Execution time" + (endTime - startTime) / 1_000_000.0);
    }

    @Transactional
    public void saveParallel(Account account) {
        accountRepository.save(account);
    }
}
