package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;

public class EntryHandler implements Runnable{
	public AdmissionControl admissionControl=new AdmissionControl();

	volatile boolean done=false;
	public void run() {
		
			while(true) {
				if(done) {
					break;
				}
				admissionControl.enter();
				try {
					Thread.sleep(1000);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		
		
		
	}
	public void setDone() {
		
		System.out.println("Entry done");
		done=true;
		
	}
}
