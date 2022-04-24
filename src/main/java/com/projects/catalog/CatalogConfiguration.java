package com.projects.catalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;

@Configuration
public class CatalogConfiguration {

    @Bean
    public ConversionService conversionService() {
        return new GenericConversionService();
    }
}
