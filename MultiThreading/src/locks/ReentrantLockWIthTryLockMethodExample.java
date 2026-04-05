package locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWIthTryLockMethodExample {

    public static void main(String[] args){

        BankAccount account
                = new SBIAccount(); // Shared resource

        // Thread 1 to deposit money into the account
        Thread t1 = new Thread(() -> {
                account.deposit(200);
                try {
                    Thread.sleep(50); // Simulate some delay
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

        },"t1");

        // Thread 2 to withdraw money from the account
        Thread t2 = new Thread(() -> {
                account.withdraw(100);
                try {
                    Thread.sleep(
                            100); // Simulate some delay
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
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

class SBIAccount extends BankAccount{

    // Shared resource (bank balance)
    private int balance = 1000;

    Lock lock = new ReentrantLock();

    // Synchronized method for deposit operation
    @Override
    public void deposit(int amount){
        System.out.println(
                Thread.currentThread().getName()
                        + " acquired deposit Lock.");

        try{
            if(lock.tryLock(10000, TimeUnit.MILLISECONDS)){
                if(balance >= amount){
                    balance += amount;
                    System.out.println("Deposited: " + amount
                            + ", Balance: " + balance);
                }
            }
        }
        catch (Exception e){
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        finally {
            lock.unlock();
            System.out.println(
                    Thread.currentThread().getName()
                            + " unlocked deposit Lock.");
        }

    }

    // Synchronized method for withdrawal operation
    @Override
    public  void withdraw(int amount){

        System.out.println(
                Thread.currentThread().getName()
                        + " acquired withdraw Lock. ");
        try{
            if(lock.tryLock(10000,TimeUnit.MILLISECONDS)){

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

        }
        catch (Exception e){
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        finally {
            lock.unlock();
            System.out.println(
                    Thread.currentThread().getName()
                            + " unlocked withdraw Lock.");
        }


    }

    public int getBalance() { return balance; }
}

