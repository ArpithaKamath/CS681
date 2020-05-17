package edu.umb.cs681.hw05;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
    private Boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeGenerator(Long from, Long to) {
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
    public void generatePrimes(){
        for(long ip= from; ip<=to; ip++){
            lock.lock();
            try{
                if(done) {
                    System.out.println("Stopped...");
                    this.primes.clear();
                    break;
                }
                if(isPrime(ip)){
                    this.primes.add(ip);
                }
            }finally{
                lock.unlock();
            }
        }

        lock.lock();
        try {
            done = true;
        }finally{
            lock.unlock();
        }

    }
    public void run(){
        generatePrimes();
    }
}
