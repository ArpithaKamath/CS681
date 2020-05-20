package edu.umb.cs681.hw12;

import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {
	
	private AccessCounter accessCounter;
	volatile boolean done = false;
	ReentrantLock lock=new ReentrantLock();
	
	public RequestHandler(AccessCounter ac){
		accessCounter = ac;
	}
	
	public void setDone(){
		lock.lock();
		done = true;
		lock.unlock();
	}
	
	@Override
	public void run() {
		lock.lock();
		int rand = (int) (Math.random()*5);
		String[] files = {"index.html","foo.html","new.html","a.html","b.html"};
		String  file = files[rand];
		accessCounter.increment(Paths.get(file));
		accessCounter.getCount(Paths.get(file));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}

}
