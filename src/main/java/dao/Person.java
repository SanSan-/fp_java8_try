package dao;

import java.util.Optional;

public class Person {

	private Optional<Car> car;
	private Integer age;
	private String name;

	public Person() {
		car = Optional.empty();
	}

	public static Person withCar(Car car) {
		Person person = new Person();
		person.setCar(car);
		return person;
	}

	public Person withAge(Integer age) {
		Person person = Person.this;
		person.setAge(age);
		return person;
	}

	public Person withName(String name) {
		Person person = Person.this;
		person.setName(name);
		return person;
	}

	public static Person withInsuranceName(String name) {
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

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "[Person = {Name: " + name
				+ ", Age: " + age
				+ ", Car: " + car.orElse(Car.empty()).toString()
				+ "}]";
	}
}
