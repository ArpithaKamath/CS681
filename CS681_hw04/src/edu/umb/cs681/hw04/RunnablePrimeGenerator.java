package edu.umb.cs681.hw04;


public class RunnablePrimeGenerator extends PrimeGenerator
        implements Runnable {

    public RunnablePrimeGenerator(Long from, Long to){
        super(from, to);
    }

    public void run(){
        generatePrimes();
    }
}