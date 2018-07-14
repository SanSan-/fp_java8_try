package services;

import dao.Car;
import dao.Insurance;
import dao.Person;
import java.util.Optional;
import monads.validation.Validation;

import static monads.validation.Failure.failure;
import static monads.validation.Success.success;

public class DaoUtil {

	public final static String WARNING_PERSON_AGE = "[Warning] Person age must be between 0 and 130!";
	public final static String WARNING_PERSON_NAME = "[Warning] Person name must start with uppercase!";
	public final static String ERROR_NO_OBJECT = "[Error] There is no object!";
	public final static String DEFAULT = "Unknown";

	public static String getCarInsuranceName(Person person) {
		return Optional.ofNullable(person)
				.flatMap(Person::getCar)
				.flatMap(Car::getInsurance)
				.map(Insurance::getName)
				.orElse(DEFAULT);
	}

	public static Person unbox(Person person) {
		return Optional.ofNullable(person).orElse(new Person());
	}

	public static Validation<String, Person> validateAge(Person person) {
		Optional<Integer> age = Optional.ofNullable(unbox(person).getAge());
		return age.isPresent() ? ((age.get() > 0 && age.get() < 130) ?
				success(person) : fail(WARNING_PERSON_AGE, person)) : fail(ERROR_NO_OBJECT, unbox(person));
	}

	public static Validation<String, Person> validateName(Person person) {
		Optional<String> name = Optional.ofNullable(unbox(person).getName());
		return name.isPresent() ? ((Character.isUpperCase(name.get().charAt(0))) ?
				success(person) : fail(WARNING_PERSON_NAME, person)) : fail(ERROR_NO_OBJECT, unbox(person));
	}

	private static Validation<String, Person> fail(String s, Person p) {
		System.out.println(s + p.toString());
		return failure(s, p);
	}
}
