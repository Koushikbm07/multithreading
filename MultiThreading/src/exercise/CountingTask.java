package exercise;

public class CountingTask {


    public static void main(String[] args) throws InterruptedException {

        Addition addtion = new Addition();

        Thread worker1 = new Thread(()-> addtion.count(0,Integer.MAX_VALUE/2));
        Thread worker2 = new Thread(()-> addtion.count(Integer.MAX_VALUE/2+1,Integer.MAX_VALUE));

        worker1.start();
        worker2.start();

        worker1.join();
        worker2.join();


        System.out.println("sum "+ addtion.sum);


    }
}
