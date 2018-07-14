package dao;

import java.util.Optional;

public class Person {

	private Optional<Car> car;

	public Person() {
		car = Optional.empty();
	}

	public static Person withCar(Car car) {
		Person person = new Person();
		person.setCar(car);
		return person;
	}

	public static Person withName(String name) {
		return Person.withCar(Car.withInsurance(Insurance.withName(name)));
	}

	public static Person empty() {
		return Person.withCar(Car.withInsurance(Insurance.empty()));
	}

	public Optional<Car> getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = Optional.ofNullable(car);
	}
}
