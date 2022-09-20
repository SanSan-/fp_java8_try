package services;

import dao.Person;
import org.junit.jupiter.api.Test;

import static monads.transaction.StartedTransaction.start;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionManagerTest {

    @Test
    public void runInTx() {
        Person person = TransactionManager
                .runInTx(() -> Person.withInsuranceName("Monty").withAge(27).withName("Colin"), 1000L);
        assertNotNull(person);
        assertEquals("Monty", person.getCar().get().getInsurance().get().getName());
        assertEquals(27, (int) person.getAge());
        assertEquals("Colin", person.getName());
    }

    @Test
    public void transactions() {
        assertTrue(start().isStarted());
        assertFalse(start().isSuccess());
        assertTrue(start().map(() -> Person.empty()).isStarted());
        assertFalse(start().map(() -> Person.empty()).isSuccess());
        assertFalse(start().map(() -> Person.empty()).commit().isStarted());
        assertTrue(start().map(() -> Person.empty()).commit().isSuccess());
        assertFalse(start().map(() -> Person.empty()).rollback().isStarted());
        assertFalse(start().map(() -> Person.empty()).rollback().isSuccess());
        assertNull(start().map(() -> Person.empty()).rollback().getStore());
        assertNull(start().commit().getStore());
    }
}
