package deadlock;


public class DeadLockExample {
    public static void main(String[] args)  {

        Pen pen = new Pen();
        Paper paper = new Paper();


        Thread t1=new Thread(new Task1(pen,paper),"t1");
        Thread t2=new Thread(new Task2(pen,paper),"t2");

        t1.start();
        t2.start();



    }
}

class Pen{

    public synchronized void writeWithPenAndPaper(Paper paper){
        System.out.println(Thread.currentThread().getName()+" pen is trying to write");
        paper.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println( Thread.currentThread().getName() +" finishWriting");
    }
}

class Paper{

    public synchronized void writeWithPenAndPaper(Pen pen){
        System.out.println(Thread.currentThread().getName()+" paper is trying to write");
        pen.finishWriting();
    }

    public  synchronized void finishWriting() {
        System.out.println( Thread.currentThread().getName() +" finishWriting");
    }
}


class Task1 implements Runnable{

    private Pen pen;
   private  Paper paper;

    public Task1(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"  running");

        //paper object should not be locked to execute the critical section
        synchronized (paper){
            pen.writeWithPenAndPaper(paper);

        }

    }
}

class Task2 implements Runnable{

    private Pen pen;
   private  Paper paper;

    public Task2(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"  running");
        paper.writeWithPenAndPaper(pen);
    }
}

