package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WhyReentrantLock {

    public static void main(String[] args) {
        ReentrantLockEx ex=new ReentrantLockEx();
        ex.outerMethod();
    }

}

class ReentrantLockEx {

    Lock lock= new ReentrantLock();

    public void outerMethod(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" outerMethod");
            innerMethod();
        } catch (Exception e) {

        }
        finally {
            lock.unlock();
        }
    }

    void innerMethod(){
        lock.lock();
        try{
         System.out.println(Thread.currentThread().getName()+" innerMethod");
        } catch (Exception e) {

        }
        finally {
            lock.unlock();
        }
    }
}
