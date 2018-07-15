import dao.Person;
import org.junit.Test;
import services.DaoUtil;

import static junit.framework.Assert.assertEquals;
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
