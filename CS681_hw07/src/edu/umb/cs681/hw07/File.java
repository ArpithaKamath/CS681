package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class File {
	ReentrantLock lock = new ReentrantLock();
	long startTime = System.currentTimeMillis();
	private boolean changed = false;
	public File aFile;
	
	public 	File(){
		aFile=this;
	}
	
	public void change(){
		lock.lock();
		try{			
			System.out.println("Changes The File Content!!");
			changed = true;
		}finally{
			lock.unlock();
		}
	}
	
	public void save(){
		lock.lock();
		try{
			if(changed == false) return;
			if(changed == true){
			long difference = System.currentTimeMillis();;
		    System.out.println("File saved at: " + difference +"ms");
			changed = false;
			}
		}finally{
			lock.unlock();
		}
	}
	
}
