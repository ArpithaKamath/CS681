package edu.umb.cs681.hw06;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        try{
            System.out.println("Execute with 2 threads. ");
            RunnableCancellablePrimeFactorizer g1 = new RunnableCancellablePrimeFactorizer(2489, 2, (long)Math.sqrt(2489)/2 );
            RunnableCancellablePrimeFactorizer g2 = new RunnableCancellablePrimeFactorizer(3689, 2, (long)Math.sqrt(2489)/2 );

            Thread t1 = new Thread(g1);
            Thread t2 = new Thread(g2);
            long startTime = System.currentTimeMillis();
            t1.start(); t2.start();
            t1.join();  t2.join();
            long endTime = System.currentTimeMillis();
            long timeElapsed =  endTime - startTime;
            g1.generatePrimeFactors();
            g2.generatePrimeFactors();
			try{
				Thread.sleep(1000);
			} catch(Exception e){e.printStackTrace();}
			System.out.println("Prime Factors 1st Thread" + g1.getPrimeFactors()); 
            System.out.println("Prime Factors 2nd Thread" + g2.getPrimeFactors()); 
			g1.setDone();
			g2.setDone();

        }catch (Exception ex){
			ex.printStackTrace();
        }
    }
}
