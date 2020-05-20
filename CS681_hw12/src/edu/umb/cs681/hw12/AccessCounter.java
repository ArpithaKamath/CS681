package edu.umb.cs681.hw12;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.nio.file.Path;

public class AccessCounter {

    private static AccessCounter accessCounter =null;
    HashMap<java.nio.file.Path, Integer> HashMapPath = new HashMap<java.nio.file.Path, Integer>();
    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static ReentrantLock locker=new ReentrantLock();
    
    public AccessCounter() {
        //
    }
     public static AccessCounter getInstance() {
    	 locker.lock();
    	 try {
    		 if (accessCounter==null) {
        		 accessCounter=new AccessCounter();
        	 }
        	 return accessCounter;
    	 }finally {
    		 locker.unlock();
    	 }
    	 
    	 
     }

    public void increment(Path path){
        rwLock.writeLock().lock();
        try{
            if(HashMapPath.containsKey(path)){
                HashMapPath.put(path, HashMapPath.get(path)+1);
            }else{
                HashMapPath.put(path,1);
            }
        }finally{
            rwLock.writeLock().unlock();
        }
    }

    public int getCount(Path path){
        rwLock.readLock().lock();
        try{
            if(HashMapPath.containsKey(path)){
                return HashMapPath.get(path);
            }else{
                return 0;
            }
        }finally{
        	System.out.println("Access counter for file "+ path.getFileName()+" is "+HashMapPath.get(path));
            rwLock.readLock().unlock();
        }
    }

    public static void main(String []args){
        AccessCounter ac = AccessCounter.getInstance();
        RequestHandler rh = new RequestHandler(ac);
        Thread t1 = new Thread(rh);
        Thread t2 = new Thread(rh);
        Thread t3 = new Thread(rh);
        Thread t4 = new Thread(rh);
        Thread t5 = new Thread(rh);
        Thread t6 = new Thread(rh);
        Thread t7 = new Thread(rh);
        Thread t8 = new Thread(rh);
        Thread t9 = new Thread(rh);
        Thread t10 = new Thread(rh);

        t1.start();t2.start();
        t3.start();t4.start();
        t5.start();t6.start();
        t7.start();t8.start();
        t9.start();t10.start();
        //new Thread(rh).start();
        rh.setDone();
        
    }
}
