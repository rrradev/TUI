package com.tui.stepDefinitions.config;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableEntryTransformer;

import java.lang.reflect.Type;
import java.util.Map;

public class Transformers {

    private final static ObjectMapper mapper = new ObjectMapper();

    @DefaultDataTableEntryTransformer(replaceWithEmptyString = "[empty]")
    public Object transform(Map<String, String> entry, Type toValueType) {
        return mapper.convertValue(entry, mapper.constructType(toValueType));
    }
}
