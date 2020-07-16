package dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

import static dao.FilterModificator.EQUAL;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Filter {

    private String key;
    private List<String> values;
    private FilterModificator modificator;

    public static Filter of(String key, String value) {
        return of(key, value, EQUAL);
    }

    public static Filter of(String key, String value, FilterModificator modificator) {
        return new Filter(key, Collections.singletonList(value), modificator);
    }

    public static Filter of(String key, List<String> values) {
        return of(key, values, EQUAL);
    }

    public static Filter of(String key, List<String> values, FilterModificator modificator) {
        return new Filter(key, values, modificator);
    }

}
