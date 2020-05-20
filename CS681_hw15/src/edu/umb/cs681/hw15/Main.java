package edu.umb.cs681.hw15;



public class Main {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//PieChartObserver PiechartObserver = new PieChartObserver();
		//TableObserver TableObserver = new TableObserver();
		//ThreeDObserver THREEDObserver = new ThreeDObserver();

		StockQuoteObservable stockObserver = new StockQuoteObservable();
		Thread t1=new Thread(stockObserver);
		t1.start();
		
		Thread t3=new Thread(stockObserver);
		t3.start();
		DJIAQuoteObservable djiaObserver = new DJIAQuoteObservable();
		Thread t2=new Thread(djiaObserver);
		t2.start();
		try {
			t1.join();t2.join();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		}

}
