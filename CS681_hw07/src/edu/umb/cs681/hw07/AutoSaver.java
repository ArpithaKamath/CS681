package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

class AutoSaver extends File implements Runnable {
	
	private boolean done = false;
	ReentrantLock lock = new ReentrantLock();
	//public File aFile = new File();
	private static int count=0;
	
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			lock.lock();
			try{				
				if(done==true){
					System.out.println("Set to true");
				break;
				}
				save();
				Thread.sleep(20);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				lock.unlock();
				
			}
		}
	}
	
	public void setDone(){
		lock.lock();
		try{
			System.out.println("donr");
			done = true;
		}finally{
			lock.unlock();
		}
	}

}
