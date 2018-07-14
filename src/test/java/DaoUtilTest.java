import dao.Person;
import org.junit.Test;
import services.DaoUtil;

import static junit.framework.Assert.assertEquals;
import static services.DaoUtil.DEFAULT;

public class DaoUtilTest {

	@Test
	public void getCarInsuranceName() {
		Person person1 = new Person();
		assertEquals(DEFAULT, DaoUtil.getCarInsuranceName(person1));
		String nameAlex = "Alex";
		Person person2 = Person.withName(nameAlex);
		assertEquals(nameAlex, DaoUtil.getCarInsuranceName(person2));
	}

}
