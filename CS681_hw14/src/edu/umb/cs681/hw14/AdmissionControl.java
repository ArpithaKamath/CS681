package edu.umb.cs681.hw14;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionControl {

    AtomicInteger atomicInt = new AtomicInteger(0);
    AdmissionControl admissionControl;
    private static ReentrantLock lock = new ReentrantLock();
    private static AdmissionControl instance = null;
    private static int currentVisitors=0;
    private Condition conditionEntry=lock.newCondition();;
    private Condition conditionExit=lock.newCondition();;

   

    public void enter(){
        lock.lock();
        try {
        	 while(currentVisitors>=5) {
             	System.out.println("Too many visitors. Please wait for a while");
             	try {
             		conditionEntry.await();
             	}catch(Exception e) {
             		e.printStackTrace();
             	}
             }
             
             currentVisitors++;
             conditionExit.signalAll();
             System.out.println("After Entry Count= "+ currentVisitors);
          
        }
       finally {
        	lock.unlock();
        }
        
    }

    public void exit(){

        lock.lock();
        try {
        	while(currentVisitors<=0) {
        		try {
        			System.out.println("Wait for a while");
        			conditionExit.await();
        		}catch (Exception e) {
					// TODO: handle exception
        			e.printStackTrace();
				}
        	}
        	currentVisitors--;
        	conditionEntry.signalAll();
        	System.out.println("After Exit Count="+ currentVisitors);    
        }
        finally {
        	lock.unlock();
        }
    }

    public int countCurrentVisitors(){
    	lock.lock();
    	try {
    		System.out.println("Count "+ currentVisitors+" Id " + Thread.currentThread().getId());
            return currentVisitors;
    	}
    	finally {
    		lock.unlock();
    	}
        

    }


    public static AdmissionControl getInstance() {
        // TODO Auto-generated method stub
        if(instance==null){
            lock.lock();
            try{
                instance = new AdmissionControl();
                
            }finally{
                lock.unlock();
            }
        }
        return instance;
    }
}
