import Thread.MyThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class  MultiThreading {
    public static void main(String[] args) {

        //create a Thread by extending Thread class
        MyThread myThread = new MyThread();
        myThread.setName("MyThread");
        myThread.start();


        //create a Thread usin  g Runnable Interface

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running "+Thread.currentThread().getName()+" from Runnable Interface");

            }
        };


        Thread runnableThread = new Thread(runnable);
        runnableThread.setName("RunnableThread");
        runnableThread.start();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
    }
}