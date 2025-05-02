package com.benjamin.ecommerce.shared;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonStringBuilder {
    private Map<String, Object> map = new HashMap<>();

    public JsonStringBuilder(String key, String value) {
        this.map.put(key, value);
    }

    public JsonStringBuilder record(String key, String value) {
        this.map.put(key, value);
        return this;
    }

    public String build() {
        try {
            return new ObjectMapper().writeValueAsString(this.map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
