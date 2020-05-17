package edu.umb.cs681.hw01;

public class MyExampleObservable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//PieChartObserver PiechartObserver = new PieChartObserver();
		//TableObserver TableObserver = new TableObserver();
		//ThreeDObserver THREEDObserver = new ThreeDObserver();

		StockQuoteObservable stockObserver = new StockQuoteObservable();
		
		
		stockObserver.addObserver((Observable o,Object obj)->{System.out.println("Added new Observer for Stock");});
		
		stockObserver.changeQuote("Nancy", 2000);
		

		DJIAQuoteObservable djiaObserver = new DJIAQuoteObservable();
		
		djiaObserver.addObserver((Observable o,Object obj)->{System.out.println("Added new Observer");});

		djiaObserver.changeQuote("Paul", 9000);
		
		}

}
