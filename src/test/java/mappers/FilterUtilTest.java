package mappers;

import dao.Filter;
import dao.FilterModificator;
import org.junit.Test;
import services.FilterUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static dao.FilterModificator.EQUAL;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class FilterUtilTest {

    final FilterUtil filterUtil = new FilterUtil();

    @Test
    public void test_mapToFilterList_empty_success() {
        List<Filter> filters = filterUtil.mapToFilterList(new HashMap<>());
        assertEquals(0, filters.size());
    }

    @Test
    public void test_mapToFilterList_success() {
        HashMap<String, Object> params = new HashMap<>();
        String expectedId = "1234-id";
        params.put("id", expectedId);
        List<Filter> filters = filterUtil.mapToFilterList(params);
        assertEquals(1, filters.size());
        checkFilter(filters, "objectId", EQUAL, expectedId);
    }

    private void checkFilter(List<Filter> filters,
                             String expectedKey,
                             FilterModificator expectedModificator,
                             String... values) {
        Optional<Filter> actual = filters.stream()
            .filter(filter -> Objects.equals(filter.getKey(), expectedKey))
            .findFirst();
        assertTrue(actual.isPresent());
        assertEquals(expectedKey, actual.get().getKey());
        assertEquals(expectedModificator, actual.get().getModificator());
        assertEquals(values.length, actual.get().getValues().size());
        for (String value : values) {
            assertTrue(actual.get().getValues().contains(value));
        }
    }
}
