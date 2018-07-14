import dao.Person;
import org.junit.Test;
import services.DaoUtil;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
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

	@Test
	public void validation() {
		assertFalse(DaoUtil.validateAge(null).isSuccess());
		assertFalse(DaoUtil.validateAge(new Person()).isSuccess());
		assertFalse(DaoUtil.validateAge(Person.empty()).isSuccess());
		assertFalse(DaoUtil.validateAge(Person.empty().withAge(0)).isSuccess());
		assertFalse(DaoUtil.validateAge(Person.empty().withAge(130)).isSuccess());
		assertTrue(DaoUtil.validateAge(Person.empty().withAge(1)).isSuccess());
		assertTrue(DaoUtil.validateAge(Person.empty().withAge(129)).isSuccess());

		assertFalse(DaoUtil.validateName(null).isSuccess());
		assertFalse(DaoUtil.validateName(new Person()).isSuccess());
		assertFalse(DaoUtil.validateName(Person.empty()).isSuccess());
		assertFalse(DaoUtil.validateName(Person.empty().withName("sammy")).isSuccess());
		assertFalse(DaoUtil.validateName(Person.empty().withName("sAmmy")).isSuccess());
		assertFalse(DaoUtil.validateName(Person.empty().withName("saMmy")).isSuccess());
		assertFalse(DaoUtil.validateName(Person.empty().withName("samMy")).isSuccess());
		assertFalse(DaoUtil.validateName(Person.empty().withName("sammY")).isSuccess());
		assertTrue(DaoUtil.validateName(Person.empty().withName("Sammy")).isSuccess());
	}

}
