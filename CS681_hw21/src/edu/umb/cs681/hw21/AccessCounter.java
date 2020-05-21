package edu.umb.cs681.hw21;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
    	 ExecutorService executor = Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() );
         ExecutorCompletionService<LinkedList<Long>> completionService = new ExecutorCompletionService<>(executor);
        AccessCounter ac = AccessCounter.getInstance();
        RequestHandler rh = new RequestHandler(ac);
        for(int i=0;i<10;i++) {
        	executor.execute(rh);
        }
        executor.shutdown();
        try {
        	executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
        
        //new Thread(rh).start();
        
    }
}
