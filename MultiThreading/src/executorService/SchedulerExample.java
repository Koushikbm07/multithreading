package executorService;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerExample {

    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // 1️⃣ schedule() → runs only once after a delay
        scheduler.schedule(() -> {
            System.out.println("schedule() - Runs once after 3 seconds: " + LocalTime.now());
        }, 3, TimeUnit.SECONDS);


        // 2️⃣ scheduleAtFixedRate() → runs repeatedly at fixed intervals
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("scheduleAtFixedRate() - Runs every 5 seconds (fixed rate): " + LocalTime.now());
            System.out.println("sleeping for 7 seconds");
            sleep(7000);
            System.out.println("slept for 7 seconds");

        }, 1, 5, TimeUnit.SECONDS);


        // 3️⃣ scheduleWithFixedDelay() → runs after previous execution completes + delay
        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println("scheduleWithFixedDelay() - Runs with 5 sec delay after task completes: " + LocalTime.now());
        }, 1, 5, TimeUnit.SECONDS);


        // Let it run for some time then shutdown
        scheduler.schedule(() -> {
            System.out.println("\nShutting down scheduler...");
            scheduler.shutdown();
        }, 25, TimeUnit.SECONDS);
    }

    // Helper method to simulate task duration
    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}