package dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

}
