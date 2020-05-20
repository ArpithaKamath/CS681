package edu.umb.cs681.hw19;


public class Car {
	
	private String make;
	private int price;
	private int year;
	private int mileage;
	private String model;
	
	

	public Car(String make,int pr,int yr, int ml,String model){
		this.make = make;
		this.price = pr;
		this.year = yr;
		this.mileage = ml;
		this.model=model;
	}
	
	public String getModel() {
		return model;
	}

	
	
	public String getMake(){
		return this.make;
	}
	
	public int getPrice(){
		return this.price;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public int getMileage(){
		return this.mileage;
	}
	
	public void display(){
		System.out.println("Make/Name of the car is:" + this.make);
		System.out.println("Price of the car "+this.make+" is:" + this.price);
		System.out.println("Year of the car is:" + this.year);
		System.out.println("Mileage of the car is:" + this.mileage);
		System.out.println("Model of the car is:" + this.model);
	}
}
