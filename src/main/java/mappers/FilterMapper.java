package mappers;

import dao.Filter;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

@AllArgsConstructor
public enum FilterMapper {
    UNKNOWN_KEY(null, emptyMapper()),
    ID_KEY("id", mapSimpleFilter("id", "objectId"));

    private final String key;
    private final BiConsumer<Map<String, Object>, List<Filter>> mapper;

    public static BiConsumer<Map<String, Object>, List<Filter>> getMapperByKey(String key) {
        for (FilterMapper filterMapper : values()) {
            if (Objects.equals(filterMapper.key, key)) {
                return filterMapper.mapper;
            }
        }
        return UNKNOWN_KEY.mapper;
    }

    private static BiConsumer<Map<String, Object>, List<Filter>> mapSimpleFilter(String paramKey, String filterKey) {
        return (params, filters) -> filters.add(Filter.of(filterKey, String.valueOf(params.get(paramKey))));
    }

    private static BiConsumer<Map<String, Object>, List<Filter>> emptyMapper() {
        return (params, filters) -> {
        };
    }

}
