package edu.umb.cs681.hw05;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        try{
            System.out.println("Execute with 2 threads. ");
            RunnableCancellablePrimeGenerator g1 = new RunnableCancellablePrimeGenerator(2L, 100L);
            RunnableCancellablePrimeGenerator g2 = new RunnableCancellablePrimeGenerator(1000001L, 2000000L);

            Thread t1 = new Thread(g1);
            Thread t2 = new Thread(g2);
            long startTime = System.currentTimeMillis();
            t1.start(); t2.start();
            t1.join();  t2.join();
            long endTime = System.currentTimeMillis();
            long timeElapsed =  endTime - startTime;
            g1.getPrimes();
            g2.getPrimes();
			try{
				Thread.sleep(100);
			} catch(Exception ex){ System.out.println(ex.getMessage());}
			g1.setDone();
			g2.setDone();
            System.out.println("Factors: " +g1.getPrimes() ); 
			
        }catch (Exception ex){

        }
    }
}
