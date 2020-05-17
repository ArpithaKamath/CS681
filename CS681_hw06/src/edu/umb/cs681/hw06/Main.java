package edu.umb.cs681.hw06;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        try{
            System.out.println("Execute with 2 threads. ");
            RunnablePrimeGenerator g1 = new RunnablePrimeGenerator(1L, 1000000L);
            RunnablePrimeGenerator g2 = new RunnablePrimeGenerator(1000001L, 2000000L);

            Thread t1 = new Thread(g1);
            Thread t2 = new Thread(g2);
            long startTime = System.currentTimeMillis();
            t1.start(); t2.start();
            t1.join();  t2.join();
            long endTime = System.currentTimeMillis();
            long timeElapsed =  endTime - startTime;
            g1.getPrimes().forEach((prime)-> System.out.println(prime));
            g2.getPrimes().forEach((prime)-> System.out.println(prime));

            System.out.println("Execution time in milliseconds: " + timeElapsed); // 597 ms

        }catch (Exception ex){

        }
    }
}
