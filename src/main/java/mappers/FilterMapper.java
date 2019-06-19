package mappers;

import dao.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class FilterMapper {

    public List<Filter> mapToFilterList(Map<String, Object> params) {
        List<Filter> filters = new ArrayList<>();
        BiConsumer<Predicate<Map<String, Object>>, BiConsumer<Map<String, Object>, List<Filter>>> fillIfExist = fillIfExist(params, filters);
        fillIfExist.accept(search -> isExist(search, "id"), this::mapIdFilter);
        return filters;
    }

    private Boolean isExist(Map<String, Object> params, String key) {
        return Objects.nonNull(params.get(key));
    }

    private BiConsumer<Predicate<Map<String, Object>>, BiConsumer<Map<String, Object>, List<Filter>>> fillIfExist(Map<String, Object> params, List<Filter> filters) {
        return (predicate, consumer) -> {
            if (predicate.test(params)) {
                consumer.accept(params, filters);
            }
        };
    }

    private void mapIdFilter(Map<String, Object> params, List<Filter> filters) {
        filters.add(Filter.of("objectId", String.valueOf(params.get("id"))));
    }

}
