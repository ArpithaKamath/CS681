package edu.umb.cs681.hw10;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        try{
            System.out.println("Execute with 2 threads. ");

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(ConcurrentSingleton.getInstance());
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(ConcurrentSingleton.getInstance());
                }
            });
            long startTime = System.currentTimeMillis();
            t1.start(); t2.start();
            t1.join();  t2.join();
            long endTime = System.currentTimeMillis();
            long timeElapsed =  endTime - startTime;

            System.out.println("Execution time in milliseconds: " + timeElapsed); // 597 ms

        }catch (Exception ex){
			ex.printStackTrace();
        }
    }
}
