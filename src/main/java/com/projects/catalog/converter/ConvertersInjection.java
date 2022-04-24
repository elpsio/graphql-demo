package com.projects.catalog.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConvertersInjection {

    private final GenericConversionService conversionService;

    @Autowired
    public ConvertersInjection(GenericConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Autowired
    private void converters(Set<Converter<?, ?>> converters) {
        converters.forEach(conversionService::addConverter);
    }
}