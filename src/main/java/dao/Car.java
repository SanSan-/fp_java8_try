package dao;

import java.util.Optional;

public class Car {

	private Optional<Insurance> insurance;

	public Car() {
		insurance = Optional.empty();
	}

	public static Car withInsurance(Insurance insurance) {
		Car car = new Car();
		car.setInsurance(insurance);
		return car;
	}

	public static Car empty() {
		return new Car();
	}

	public Optional<Insurance> getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = Optional.ofNullable(insurance);
	}

	@Override
	public String toString() {
		return "[Car = {Insurance: " + insurance.orElse(Insurance.empty()).toString() + "}]";
	}
}
