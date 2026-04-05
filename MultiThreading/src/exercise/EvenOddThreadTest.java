package exercise;

public class EvenOddThreadTest {

    public static void main(String[] args) {

        Thread evenThread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i=i+2) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        };

        Thread oddThread = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i < 100; i=i+2) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        };

        evenThread.setName("Even Thread");
        oddThread.setName("Odd Thread");
        evenThread.start();
        oddThread.start();
    }

}
