package edu.umb.cs681.hw18;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;


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
		
		System.out.println("Available Cars:");
		for(Car car:cars) {
			car.display();
		}
		System.out.println("-------------------------------");
		System.out.println("Using 1st reduce");
		Optional<Integer> price1 = cars.stream()
				.parallel()
				.map((Car car)-> car.getPrice())
				.reduce((result, carPrice)->{
				if(result==0) return carPrice;
				else if(carPrice < result) return carPrice;
				else return result;} );
		System.out.println("Price of the car with min-reduce(): "+price1);
		
		Optional<Integer> maxPrice1 = cars.stream()
				.parallel()
				.map((Car car)-> car.getPrice())
				.reduce((result, carPrice)->{
				if(result==0) return carPrice;
				else if(carPrice > result) return carPrice;
				else return result;} );
		System.out.println("Price of the car with max-reduce(): "+maxPrice1);
		
		Optional<Integer> count1 = cars.stream()
				.parallel()
				.map((Car car)-> cars.size())
				.reduce((result,carPrize)->{
				return result; } );
		System.out.println("Price of the car with count-reduce(): "+count1);

		System.out.println("-------------------------------");
		
		System.out.println("-------------------------------");
		System.out.println("Using 2nd reduce");
		Integer price = cars.stream()
				.parallel()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)->{
				if(result==0) return carPrice;
				else if(carPrice < result) return carPrice;
				else return result;} );
		System.out.println("Price of the car with min-reduce(): "+price);
		
		Integer maxPrice = cars.stream()
				.parallel()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)->{
				if(result==0) return carPrice;
				else if(carPrice > result) return carPrice;
				else return result;} );
		System.out.println("Price of the car with max-reduce(): "+maxPrice);
		
		Integer count = cars.stream()
				.parallel()
				.map((Car car)-> car.getPrice())
				.reduce(1, (result, carPrice)->{
				return ++result; } );
		System.out.println("Price of the car with count-reduce(): "+count);

		System.out.println("-------------------------------");
		System.out.println("-------------------------------");
		System.out.println("Using 3rd reduce");
		Integer price3 = cars.stream()
				.parallel()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)-> 
				result==0 ? carPrice : (carPrice < result ? carPrice : result),
				(finalResult,intermediateResult)->
				finalResult == 0 ? intermediateResult : (intermediateResult < finalResult ? intermediateResult : finalResult)
			);
		System.out.println("Price of the car with min-reduce(): "+price3);
		
		Integer maxPrice3 = cars.stream()
				.parallel()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)-> 
					result==0 ? carPrice : (carPrice > result ? carPrice : result),
					(finalResult,intermediateResult)->
					finalResult == 0 ? intermediateResult : (intermediateResult > finalResult ? intermediateResult : finalResult)
				);
		System.out.println("Price of the car with max-reduce(): "+maxPrice3);
		
		Integer count3 = cars.stream()
				.parallel()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)-> ++result , 
						(finalResult,intermediateResult)->
							finalResult + intermediateResult
						);
		System.out.println("Count of Car: "+count3);
		System.out.println("-------------------------------");
		
	}
}
