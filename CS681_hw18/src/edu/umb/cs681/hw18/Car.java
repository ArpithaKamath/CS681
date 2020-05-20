package edu.umb.cs681.hw18;


public class Car {
	
	private String make;
	private int price;
	private int year;
	private int mileage;
	
	public Car(String mdl,int pr,int yr, int ml){
		this.make = mdl;
		this.price = pr;
		this.year = yr;
		this.mileage = ml;
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
	}
}
