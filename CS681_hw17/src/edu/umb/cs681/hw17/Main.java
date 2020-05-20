package edu.umb.cs681.hw17;

public class Main {

	public static void main(String[] args) {

		Observable obs= new Observable();
		System.out.println("*--- Observer Started ---*");
		obs.setChanged();
		obs.addObserver((Observable o,Object obj)->{System.out.println(obj);});
		obs.notifyObservers("Change once");
		obs.setChanged();
		obs.addObserver((Observable o,Object obj)->{
			System.out.println(obj);
		});
		obs.notifyObservers("change again");
	}

}
