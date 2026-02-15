package org.ostech.springimplemetation.service.JavaCore.ConcurrencyAPI.ConcurrencyPatterns.ConsumerProducerPattern;

import org.springframework.stereotype.Service;

@Service
public class OrderProcessor {
    OrderExecutorQueue executorQueue;

    OrderProcessor(OrderExecutorQueue orderExecutorQueue){
        this.executorQueue = orderExecutorQueue;
    }

    public void processOrder(String orderDetails) {
        executorQueue.getOrderExecutorQueue().execute(() ->{
            try {
                updateInventory(orderDetails);
                chargePayment(orderDetails);
                shipOrder(orderDetails);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void shipOrder(String orderDetails) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("3 Order Shipped " + orderDetails + " | " + Thread.currentThread().getName());
    }

    private void chargePayment(String orderDetails)  throws InterruptedException {
        Thread.sleep(500);
        System.out.println("2 Payment Success " + orderDetails + " | " + Thread.currentThread().getName());
    }

    private void updateInventory(String orderDetails)  throws InterruptedException {
        Thread.sleep(500);
        System.out.println("1 Update Inventory " + orderDetails + " | " + Thread.currentThread().getName());
    }
}
