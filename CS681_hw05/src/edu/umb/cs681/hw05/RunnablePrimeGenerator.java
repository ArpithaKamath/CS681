package edu.umb.cs681.hw05;

import java.util.concurrent.locks.ReentrantLock;

public class RunnablePrimeGenerator extends PrimeGenerator
        implements Runnable {


    public RunnablePrimeGenerator(Long from, Long to){
        super(from, to);
    }

    public void run(){};
}
