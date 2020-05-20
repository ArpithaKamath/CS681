package edu.umb.cs681.hw14;

public class AdmissionMonitor{
	public static void main(String args[]) {
		
		EntryHandler entry=new EntryHandler();
		Thread t1=new Thread(entry);
		t1.start();
		
		try {
			Thread.sleep(2000);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		MonitorHandler monitor=new MonitorHandler();
		Thread t2=new Thread(monitor);
		t2.start();
		ExitHandler exit=new ExitHandler();
		Thread t3=new Thread(exit);
		t3.start();
		
		try {
			Thread.sleep(3000);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		entry.setDone();exit.setDone();
		try {
			t1.join();t2.join();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
	}
	
}
