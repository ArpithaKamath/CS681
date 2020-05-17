package edu.umb.cs681.hw06;

import java.util.concurrent.locks.ReentrantLock;

public class RunnablePrimeGenerator extends PrimeGenerator
        implements Runnable {

    private boolean done = false;
    ReentrantLock lock = new ReentrantLock();

    public RunnablePrimeGenerator(Long from, Long to){
        super(from, to);
    }

    public void setDone(){
        lock.lock();
        try{
            done = true;
        }finally{
            lock.unlock();
        }
    }

    public void generatePrimes() {
        for(long ip= from; ip<=to; ip++){
            try{
                lock.lock();
                if(done==true){
                    System.out.println("Stopped generating prime numbers.");
                    this.primes.clear();
                    break;
                }
                if(IsPrime(ip)){
                    primes.add(ip);
                }
            }finally{
                lock.unlock();
            }

        }
    }
    public void run(){
        generatePrimes();
    }
}
