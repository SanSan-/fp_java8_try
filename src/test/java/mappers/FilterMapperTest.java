package mappers;

import dao.Filter;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class FilterMapperTest {

    final FilterMapper filterMapper = new FilterMapper();

    @Test
    public void test_mapToFilterList_empty_success() {
        List<Filter> filters = filterMapper.mapToFilterList(new HashMap<>());
        assertEquals(0, filters.size());
    }

    @Test
    public void test_mapToFilterList_success() {
        HashMap<String, Object> params = new HashMap<>();
        String expectedId = "1234-id";
        params.put("id", expectedId);
        List<Filter> filters = filterMapper.mapToFilterList(params);
        assertEquals(1, filters.size());
        checkFilter(filters.get(0), "objectId", expectedId);
    }

    private void checkFilter(Filter filter, String key, String... values) {
        assertEquals(key, filter.getKey());
        for (String value : values) {
            assertTrue(filter.getValues().contains(value));
        }
    }
}
