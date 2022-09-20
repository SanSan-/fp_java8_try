package services;

import dao.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static services.DaoUtil.DEFAULT;

public class DaoUtilTest {

    @Test
    public void getCarInsuranceName() {
        String nameAlex = "Alex";
        assertEquals(DEFAULT, DaoUtil.getCarInsuranceName(null));
        assertEquals(DEFAULT, DaoUtil.getCarInsuranceName(new Person()));
        assertEquals(DEFAULT, DaoUtil.getCarInsuranceName(Person.empty()));
        assertEquals(nameAlex, DaoUtil.getCarInsuranceName(Person.withInsuranceName(nameAlex)));
    }

}
