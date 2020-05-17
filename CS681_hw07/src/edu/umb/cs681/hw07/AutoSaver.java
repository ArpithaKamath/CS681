package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

class AutoSaver extends File implements Runnable {
	
	private boolean done = false;
	ReentrantLock lock = new ReentrantLock();
	//public File aFile = new File();
	
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			lock.lock();
			try{				
				if(done==true){
					System.out.println("Set to true");
				break;
				}
				aFile.change();
				aFile.save();
				Thread.sleep(2000);
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
			done = true;
		}finally{
			lock.unlock();
		}
	}

}
