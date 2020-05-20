package edu.umb.cs681.hw13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount2 {

    private double balance = 0;
    private static ReentrantLock lock;
    private Condition sufficientFundsCondition, belowUpperLimitFundsCondition;
    private ThreadSafeBankAccount2 account;

    public ThreadSafeBankAccount2(){
        lock = new ReentrantLock();
        sufficientFundsCondition = lock.newCondition();
        belowUpperLimitFundsCondition = lock.newCondition();
        account =  this;
    }
    
    

    public void deposit(double amount){
        lock.lock();
        try{
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().getId() +
                    " (d): current balance: " + balance);
            
                while(balance >= 300){
                    System.out.println(Thread.currentThread().getId() +
                            " (d): await(): Balance exceeds the upper limit.");
                    belowUpperLimitFundsCondition.await();
                }
                balance += amount;
                System.out.println(Thread.currentThread().getId() +
                        " (d): new balance: " + balance);
                sufficientFundsCondition.signalAll();
            
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("Lock released");
        }
    }


    public void withdraw(double amount){
        lock.lock();
        try{
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().getId() +
                    " (w): current balance: " + balance);
            
            
                while(balance <= 0){
                    System.out.println(Thread.currentThread().getId() +
                            " (w): await(): Insufficient funds");
                    sufficientFundsCondition.await();
                }
                balance -= amount;
                System.out.println(Thread.currentThread().getId() +
                        " (w): new balance: " + balance);
                belowUpperLimitFundsCondition.signalAll();
            

        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public static void main(String[] args){
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
        DepositRunnable dr=new DepositRunnable(bankAccount);
        WithdrawRunnable wr=new WithdrawRunnable(bankAccount);
        DepositRunnable dr2=new DepositRunnable(bankAccount);
        WithdrawRunnable wr2=new WithdrawRunnable(bankAccount);
        Thread t1= new Thread(dr);
        t1.start();
           
        
        Thread t2= new Thread(wr);
        t2.start();
        
        Thread t3=new Thread(dr2);
        t3.start();
        
        Thread t4=new Thread(wr2);
        t4.start();
        
        try {
        	Thread.sleep(1000);
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        dr.setDone();
        wr.setDone();
        dr2.setDone();
        wr2.setDone();
        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
        t4.interrupt();
        try {
        	t1.join();t2.join();t3.join();t4.join();
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }

    


}
