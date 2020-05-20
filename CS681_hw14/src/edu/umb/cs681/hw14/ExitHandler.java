package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;

public class ExitHandler implements Runnable {
	private AdmissionControl admissionControl=new AdmissionControl();
	public boolean done=false;
	public void run() {
		
			while(true) {
				if(done) {
					break;
				}
				admissionControl.exit();
				try {
					Thread.sleep(1000);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		
	}
	public void setDone() {
		
		System.out.println("Exit done");
		done=true;
		
	}

}
