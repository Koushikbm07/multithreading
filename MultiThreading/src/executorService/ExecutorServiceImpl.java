package executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceImpl {

    public static void main(String[] args)  {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName());
            }
        });
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main eneded");
    }
}
