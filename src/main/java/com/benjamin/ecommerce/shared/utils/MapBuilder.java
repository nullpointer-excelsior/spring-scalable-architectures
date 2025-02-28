package com.benjamin.ecommerce.shared.utils;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder <T>{
    private Map<String, T> map = new HashMap<>();

    public MapBuilder<T> record(String key, T value) {
        map.put(key, value);
        return this;
    }

    public Map<String, T> build() {
        return map;
    }
}
