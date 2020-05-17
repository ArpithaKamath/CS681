package edu.umb.cs681.hw02;


public class Car {
	
	private String model;
	private int price;
	private int year;
	private int mileage;
	
	public Car(String mdl,int pr,int yr, int ml){
		this.model = mdl;
		this.price = pr;
		this.year = yr;
		this.mileage = ml;
	}
	
	public String getModel(){
		return this.model;
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
		System.out.println("Model/Name of the car is:" + this.model);
		System.out.println("Price of the car "+this.model+" is:" + this.price);
		System.out.println("Year of the car is:" + this.year);
		System.out.println("Mileage of the car is:" + this.mileage);
	}
}
