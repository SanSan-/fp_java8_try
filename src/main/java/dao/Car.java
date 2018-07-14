package dao;

import java.util.Optional;

public class Car {

	private Optional<Insurance> insurance;

	public static Car withInsurance(Insurance insurance) {
		Car car = new Car();
		car.setInsurance(insurance);
		return car;
	}

	public Optional<Insurance> getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = Optional.ofNullable(insurance);
	}
}
