package edu.umb.cs681.hw13;


public class WithdrawRunnable implements Runnable{
	private ThreadSafeBankAccount2 account;
	volatile boolean done=false;

	
	public WithdrawRunnable(ThreadSafeBankAccount2 account) {
		this.account = account;
	}
	
	public void run(){
		for(int i = 0; i < 10; i++){
			if(done) {
				System.out.println("Deposit Done");
				break;
			}
			account.withdraw(100);
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
