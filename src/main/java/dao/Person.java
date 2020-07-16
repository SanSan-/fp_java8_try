package dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@Getter
@Setter
@ToString
public class Person {

    private Optional<Car> car;
    private Integer age;
    private String name;

    public Person() {
        car = Optional.empty();
    }

    public static Person withCar(Car car) {
        Person person = new Person();
        person.setCar(Optional.ofNullable(car));
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
}
