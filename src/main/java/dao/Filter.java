package dao;

import java.util.Collections;
import java.util.List;

import static dao.FilterModificator.EQUAL;

public class Filter {

    private String key;
    private List<String> values;
    private FilterModificator modificator;

    private Filter(String key, List<String> values, FilterModificator modificator) {
        this.key = key;
        this.values = values;
        this.modificator = modificator;
    }

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

    public String getKey() {
        return key;
    }

    public FilterModificator getModificator() {
        return modificator;
    }

    public List<String> getValues() {
        return values;
    }
}
