package services;

import dao.Car;
import dao.Insurance;
import dao.Person;
import java.util.Optional;

public class DaoUtil {

	public final static String DEFAULT = "Unknown";

	public static String getCarInsuranceName(Person person) {
		return Optional.ofNullable(person)
				.flatMap(Person::getCar)
				.flatMap(Car::getInsurance)
				.map(Insurance::getName)
				.orElse(DEFAULT);
	}

}
