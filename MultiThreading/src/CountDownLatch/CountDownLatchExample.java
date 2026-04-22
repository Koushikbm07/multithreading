package CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {

        int numberOfTasks = 3;

        CountDownLatch latch = new CountDownLatch(numberOfTasks);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("Main thread is waiting for tasks to complete...\n");

        // Submit 3 tasks
        for (int i = 1; i <= numberOfTasks; i++) {
            int taskId = i;

            executor.submit(() -> {
                try {
                    System.out.println("Task " + taskId + " started...");
                    Thread.sleep(2000 * taskId); // simulate work
                    System.out.println("Task " + taskId + " completed");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown(); // reduce count
                    System.out.println("Latch count decreased. Remaining: " + latch.getCount());
                }
            });
        }

        // Main thread waits here
        latch.await();

        System.out.println("\nAll tasks completed. Main thread resumes.");

        executor.shutdown();
    }
}