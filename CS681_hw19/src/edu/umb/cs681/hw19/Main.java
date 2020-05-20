package edu.umb.cs681.hw19;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class Main {

	public static void main(String []args)
	{
		ArrayList<Car> cars = new ArrayList<Car>();
		Car c1 = new Car("Honda", 30000, 2013, 22,"CRV");
		Car c2 = new Car("Kia", 29000, 2014, 15,"Escape");
		Car c3 = new Car("Audi", 60000, 2015, 11,"R8");
		Car c4 = new Car("BMW",65000, 2016, 9,"CX-5");
		
		cars.add(c1);
		cars.add(c2);
		cars.add(c3);
		cars.add(c4);
		System.out.println("Available Cars:");
		for(Car car:cars) {
			car.display();
		}
		Integer price = cars.stream()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)-> 
				result==0 ? carPrice : (carPrice < result ? carPrice : result),
				(finalResult,intermediateResult)->
				finalResult == 0 ? intermediateResult : (intermediateResult < finalResult ? intermediateResult : finalResult)
			);
		System.out.println("Price of the car with min-reduce(): "+price);
		
		Integer maxPrice = cars.stream()
				.parallel()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)-> 
					result==0 ? carPrice : (carPrice > result ? carPrice : result),
					(finalResult,intermediateResult)->
					finalResult == 0 ? intermediateResult : (intermediateResult > finalResult ? intermediateResult : finalResult)
				);
		System.out.println("Price of the car with max-reduce(): "+maxPrice);
		
		Integer count = cars.stream()
				.parallel()
				.map((Car car)-> car.getPrice())
				.reduce(0, (result, carPrice)-> ++result , 
						(finalResult,intermediateResult)->
							finalResult + intermediateResult
						);
		System.out.println("Count of Car: "+count);
		
		Integer model = cars.stream()
						.parallel()
						.map((Car car)->car.getModel())
						.reduce(0,(result, carModel)->
						result==0 ? carModel.length() : (carModel.length() < result ? carModel.length() : result),
								(finalResult,intermediateResult)->
								finalResult == 0 ? intermediateResult : (intermediateResult < finalResult ? intermediateResult : finalResult)
							);
		System.out.println("Min length of Car Model: "+model);
		
	}
}
