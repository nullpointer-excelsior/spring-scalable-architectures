package com.benjamin.ecommerce.shared;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class TestUtils {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
