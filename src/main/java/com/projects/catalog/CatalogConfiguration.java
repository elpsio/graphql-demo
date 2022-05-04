package com.projects.catalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.projects.catalog.scalar.MonetaryAmountScalar;

import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLScalarType;

@Configuration
public class CatalogConfiguration {

    @Bean
    public ConversionService conversionService() {
        return new GenericConversionService();
    }

    @Bean
    public GraphQLScalarType monetaryAmount() {
        return MonetaryAmountScalar.MONETARY_AMOUNT_SCALAR;
    }

}
