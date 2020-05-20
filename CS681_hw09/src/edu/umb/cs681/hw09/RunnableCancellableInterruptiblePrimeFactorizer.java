package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
	private Boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
	
	public RunnableCancellableInterruptiblePrimeFactorizer(long dividend,int from, long to) {
		super(dividend,from, to);
	}
	
	public void setDone(){
        lock.lock();
        try{
            done = true;
        }finally{
            lock.unlock();
        }
    }
    
    public void generatePrimeFactors() {
		long divisor = from;
	    while( dividend != 1 && divisor <= to ){
	    	lock.lock();
	    	try {
	    		if(done) {
	    			System.out.println("Done");
	    			this.factors.clear();
	    			break;
	    		}
	    		else {
	    			if( divisor > 2 && isEven(divisor)) {
	    	    		divisor++;
	    	    		continue;
	    	    	}
	    		    if(dividend % divisor == 0) {
	    		        factors.add(divisor);
	    		        dividend /= divisor;
	    		    }else {
	    		    	if(divisor==2){ divisor++; }
	    		    	else{ divisor += 2; }
	    		    	
	    		    }
	    		}
	    	}
	    	finally {
	    		lock.unlock();
	    	}
	    	try {
	    		Thread.sleep(1000);
	    	}catch(InterruptedException e) {
	    		System.out.println(e.getMessage().toString());
	    		continue;
	    	}
		}
	}
	
    
	
	public void run(){
		generatePrimeFactors();
	}

	public static void main(String[] args) {
		
		RunnableCancellableInterruptiblePrimeFactorizer gen2 = new RunnableCancellableInterruptiblePrimeFactorizer(2489, 2, (long)Math.sqrt(2489)/2 );
		Thread thread2 = new Thread(gen2);
		thread2.start();
		
		try {
			Thread.sleep(6000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//gen2.setDone();
		
		try {
			thread2.join();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Without any interruption:");
		gen2.getPrimeFactors().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen2.getPrimeFactors().size() + " prime factors are found.");
		
		RunnableCancellableInterruptiblePrimeFactorizer gen = new RunnableCancellableInterruptiblePrimeFactorizer(2489, 2, (long)Math.sqrt(2489)/2 );
		Thread thread = new Thread(gen);
		thread.start();
		
		try {
			thread.sleep(1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen.setDone();
		thread.interrupt();
		try {
			thread.join();
		}
		catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
		gen.getPrimeFactors().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimeFactors().size() + " prime numbers are found.");
	}
}
