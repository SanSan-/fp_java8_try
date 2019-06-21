package services;

import dao.Filter;
import mappers.FilterMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterUtil {

    public List<Filter> mapToFilterList(Map<String, Object> params) {
        List<Filter> filters = new ArrayList<>();
        params.keySet().forEach(key -> FilterMapper.getMapperByKey(key).accept(params, filters));
        return filters;
    }

}
