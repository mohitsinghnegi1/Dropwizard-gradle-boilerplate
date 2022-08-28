package com.example.helloworld;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import io.dropwizard.Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RequestContextBundle implements Bundle {

    public static final String NAME = RequestContextFilter.class.getName();
    public static final String DEFAULT_PATTERN = "/*";
    private final String pattern;

    public RequestContextBundle() {
        this(DEFAULT_PATTERN);
    }

    public RequestContextBundle(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    @Override
    public void run(Environment environment) {
        environment.servlets().addFilter(NAME, RequestContextFilter.class)
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, pattern);
    }
}

