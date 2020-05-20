package edu.umb.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;



public class Customer implements Runnable {
    private Address address;
    private Boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
	

    public void setDone(){
        lock.lock();
        try{
            done = true;
        }finally{
            lock.unlock();
        }
    }
    public Customer(Address addr){
        address = addr;
    }
    public void setAddress(Address addr){
    	lock.lock();
    	try {
    		address = addr;
    	}
    	finally {
    		lock.unlock();
    	}
        
    }

    public Address getAddress(){
            return address;
    }
    


    @Override
    public void run() {
        this.setAddress(address );
        System.out.println("CustomerDetails "+this.getAddress());
    }
}
