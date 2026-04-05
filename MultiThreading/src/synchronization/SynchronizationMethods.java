package synchronization;

public class SynchronizationMethods {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1=new MyThread(counter);
        Thread t2=new MyThread(counter);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("total count :"+counter.getCount());


    }


}


class Counter{

     private  int count=0;

     void  increment(){
         synchronized(this
         ){
        count++;

         }
    }

    int getCount(){
        return count;
    }
}

class MyThread extends Thread{
    private Counter counter;

    public MyThread(Counter counter){
        this.counter = counter;
    }

    @Override
    public void run(){
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}
