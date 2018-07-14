package dao;

public class Person {

	private Car car;

	public static Person withName(String name) {
		Insurance insurance = new Insurance();
		insurance.setName(name);
		Car cart = new Car();
		cart.setInsurance(insurance);
		Person person = new Person();
		person.setCar(cart);
		return person;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
}
