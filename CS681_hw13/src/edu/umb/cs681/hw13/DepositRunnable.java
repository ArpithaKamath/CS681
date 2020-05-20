package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;

public class DepositRunnable implements Runnable{
	private ThreadSafeBankAccount2 account;
	volatile boolean done=false;

    
	public DepositRunnable(ThreadSafeBankAccount2 account) {
		this.account = account;
	}
	
	public void run(){
		
			for(int i = 0; i < 10; i++){
				
				if(done) {
					System.out.println("Deposit Done");
					break;
				}
				account.deposit(100);
				try {
					Thread.sleep(2);
				}catch(InterruptedException exception){
					System.out.println(exception.getMessage().toString());
					continue;
				}
				
				
		}
	}
	public void setDone() {
				done=true;
	
	}
}
