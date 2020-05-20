package edu.umb.cs681.hw11;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        try{
            System.out.println("Execute with 3 threads. ");
            Customer c1 = new Customer(new Address("add1","city1","state1",123));
            Customer c2 = new Customer(new Address("add2","city2","state2",345));
            Customer c3 = new Customer(new Address("add1","city3","state1",300));
            
            

            Thread t1 = new Thread(c1);
            Thread t2 = new Thread(c2);
            Thread t3 = new Thread(c3);
            long startTime = System.currentTimeMillis();
            t1.start(); t2.start(); t3.start();
            try {
            	Thread.sleep(1000);
            }catch(Exception e) {
            	e.printStackTrace();
            }
            c3.setAddress(c3.getAddress().change("add1","city1","state1",128));
            System.out.println(c3.getAddress().toString());
            t1.join();  t2.join(); t3.join();
            long endTime = System.currentTimeMillis();
            long timeElapsed =  endTime - startTime;


            System.out.println("Execution time in milliseconds: " + timeElapsed); // 597 ms

        }catch (Exception ex){
        	ex.printStackTrace();
        }
    }
}
