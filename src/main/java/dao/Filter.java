package dao;

import java.util.Collections;
import java.util.List;

public class Filter {

    private String key;
    private List<String> values;

    private Filter(String key, List<String> values) {
        this.key = key;
        this.values = values;
    }

    public static Filter of(String key, String value) {
        return new Filter(key, Collections.singletonList(value));
    }

    public static Filter of(String key, List<String> values) {
        return new Filter(key, values);
    }

    public String getKey() {
        return key;
    }

    public List<String> getValues() {
        return values;
    }
}
