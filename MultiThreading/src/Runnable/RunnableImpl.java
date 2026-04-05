package Runnable;

public class RunnableImpl implements Runnable {
    @Override
    public void run() {
        System.out.println("Running "+Thread.currentThread().getName()+" from Runnable Interface");
    }
}
