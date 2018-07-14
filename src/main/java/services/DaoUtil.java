package services;

import dao.Car;
import dao.Insurance;
import dao.Person;

public class DaoUtil {

	public final static String DEFAULT = "Unknown";

	public static String getCarInsuranceName(Person person) {
		if (person != null) {
			Car car = person.getCar();
			if (car != null) {
				Insurance insurance = car.getInsurance();
				if (insurance != null) {
					return insurance.getName();
				}
			}
		}
		return DEFAULT;
	}

}
