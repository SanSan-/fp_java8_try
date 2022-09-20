package services;

import dao.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    public void validation() {
        assertFalse(Validator.validateAge(null).isSuccess());
        assertFalse(Validator.validateAge(new Person()).isSuccess());
        assertFalse(Validator.validateAge(Person.empty()).isSuccess());
        assertFalse(Validator.validateAge(Person.empty().withAge(0)).isSuccess());
        assertFalse(Validator.validateAge(Person.empty().withAge(130)).isSuccess());
        assertTrue(Validator.validateAge(Person.empty().withAge(1)).isSuccess());
        assertTrue(Validator.validateAge(Person.empty().withAge(129)).isSuccess());

        assertFalse(Validator.validateName(null).isSuccess());
        assertFalse(Validator.validateName(new Person()).isSuccess());
        assertFalse(Validator.validateName(Person.empty()).isSuccess());
        assertFalse(Validator.validateName(Person.empty().withName("sammy")).isSuccess());
        assertFalse(Validator.validateName(Person.empty().withName("sAmmy")).isSuccess());
        assertFalse(Validator.validateName(Person.empty().withName("saMmy")).isSuccess());
        assertFalse(Validator.validateName(Person.empty().withName("samMy")).isSuccess());
        assertFalse(Validator.validateName(Person.empty().withName("sammY")).isSuccess());
        assertTrue(Validator.validateName(Person.empty().withName("Sammy")).isSuccess());
    }

    @Test
    public void composeValidation() {
        assertFalse(Validator.validatePerson(null).isSuccess());
        assertFalse(Validator.validatePerson(new Person()).isSuccess());
        assertFalse(Validator.validatePerson(Person.empty()).isSuccess());

        assertFalse(Validator.validatePerson(Person.empty().withAge(0).withName("Sammy")).isSuccess());
        assertFalse(Validator.validatePerson(Person.empty().withAge(130).withName("Sammy")).isSuccess());

        assertFalse(Validator.validatePerson(Person.empty().withAge(1).withName("sammy")).isSuccess());
        assertFalse(Validator.validatePerson(Person.empty().withAge(129).withName("sammy")).isSuccess());

        assertTrue(Validator.validatePerson(Person.empty().withAge(1).withName("Sammy")).isSuccess());
        assertTrue(Validator.validatePerson(Person.empty().withAge(129).withName("Sammy")).isSuccess());
    }
}
