package dao;

public class Insurance {

	private String name;

	public static Insurance empty() {
		return new Insurance();
	}

	public static Insurance withName(String name) {
		Insurance insurance = empty();
		insurance.setName(name);
		return insurance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
