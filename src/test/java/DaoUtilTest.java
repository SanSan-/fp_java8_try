import dao.Person;
import org.junit.Test;
import services.DaoUtil;

import static junit.framework.Assert.assertEquals;
import static services.DaoUtil.DEFAULT;

public class DaoUtilTest {

	@Test
	public void getCarInsuranceName() {
		// also suddenly DaoUtil.getCarInsuranceName(new Persona()) generate NPE
		Person person1 = Person.empty();
		assertEquals(DEFAULT, DaoUtil.getCarInsuranceName(person1));
		String nameAlex = "Alex";
		Person person2 = Person.withName(nameAlex);
		assertEquals(nameAlex, DaoUtil.getCarInsuranceName(person2));
	}

}
