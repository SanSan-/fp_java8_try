package dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@Getter
@Setter
@ToString
public class Car {

    private Optional<Insurance> insurance;

    public Car() {
        insurance = Optional.empty();
    }

    public static Car withInsurance(Insurance insurance) {
        Car car = new Car();
        car.setInsurance(Optional.ofNullable(insurance));
        return car;
    }

    public static Car empty() {
        return new Car();
    }

}
