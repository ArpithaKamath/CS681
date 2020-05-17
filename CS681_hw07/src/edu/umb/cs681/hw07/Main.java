package edu.umb.cs681.hw07;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		File fs = new File();
		AutoSaver as = new AutoSaver();
		System.out.println("Autosaver runs every two seconds(2000ms) press Ctrl + C to interrupt the thread!! ");
		Editor e = new Editor();
		Thread t1 =  new Thread(e);
		Thread t2 =  new Thread(as);
		e.setDone();
		t1.start();
		t2.start();
		
	}

}
