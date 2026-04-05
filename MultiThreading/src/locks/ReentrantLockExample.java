package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    public static void main(String[] args){

        BankAccount account
                = new BankAccount(); // Shared resource

        // Thread 1 to deposit money into the account
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                account.deposit(200);
                try {
                    Thread.sleep(50); // Simulate some delay
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");

        // Thread 2 to withdraw money from the account
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                account.withdraw(100);
                try {
                    Thread.sleep(
                            100); // Simulate some delay
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2");

        // Start both threads
        t1.start();
        t2.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final balance
        System.out.println("Final Balance: "
                + account.getBalance());
    }

}

class BankAccount{

    // Shared resource (bank balance)
    private int balance = 1000;

    Lock lock = new ReentrantLock();

    // Synchronized method for deposit operation
    public void deposit(int amount){
        lock.lock();
        try{
            System.out.println(
                    Thread.currentThread().getName()
                            + " acquired ReentrantLock.");
            if(balance >= amount){
                balance += amount;
                System.out.println("Deposited: " + amount
                        + ", Balance: " + balance);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
            System.out.println(
                    Thread.currentThread().getName()
                            + " unlocked ReentrantLock.");
        }

    }

    // Synchronized method for withdrawal operation
    public  void withdraw(int amount){
        lock.lock();

        try{
            System.out.println(
                    Thread.currentThread().getName()
                            + " acquired ReentrantLock.");
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawn: " + amount
                        + ", Balance: " + balance);
            }
            else {
                System.out.println(
                        "Insufficient balance to withdraw: "
                                + amount);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
            System.out.println(
                    Thread.currentThread().getName()
                            + " unlocked ReentrantLock.");
        }


    }

    public int getBalance() { return balance; }
}

