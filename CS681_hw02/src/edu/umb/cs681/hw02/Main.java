package edu.umb.cs681.hw02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;

public class Main {

	public static void main(String []args)
	{
		ArrayList<Car> cars = new ArrayList<Car>();
		Car c1 = new Car("Honda", 30000, 2013, 22);
		Car c2 = new Car("Kia", 29000, 2014, 15);
		Car c3 = new Car("Audi", 60000, 2015, 11);
		Car c4 = new Car("BMW",65000, 2016, 9);
		
		cars.add(c1);
		cars.add(c2);
		cars.add(c3);
		cars.add(c4);
		//System.out.println(cars.display());
		Integer price = cars.stream()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)->{
				if(result==0) return carPrice;
				else if(carPrice < result) return carPrice;
				else return result;} );
		System.out.println("Price of the car with min-reduce(): "+price);
		
		Integer maxPrice = cars.stream()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)->{
				if(result==0) return carPrice;
				else if(carPrice > result) return carPrice;
				else return result;} );
		System.out.println("Price of the car with max-reduce(): "+maxPrice);
		
		Integer count = cars.stream()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)->{
				return ++result; } );
		System.out.println("Price of the car with max-reduce(): "+count);

	}
}
