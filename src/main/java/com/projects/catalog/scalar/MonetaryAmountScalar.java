package com.projects.catalog.scalar;

import com.projects.catalog.util.MonetaryAmount;
import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MonetaryAmountScalar extends GraphQLScalarType {

    public MonetaryAmountScalar() {
        super("MonetaryAmount", "com.projects.catalog.util.MonetaryAmount", new Coercing<MonetaryAmount, String>() {
            @Override
            public String serialize(Object o) throws CoercingSerializeException {
                return o.toString();
            }

            @Override
            public MonetaryAmount parseValue(Object o) throws CoercingParseValueException {
                String[] a = o.toString().split(" ");
                try {
                    return new MonetaryAmount(new BigDecimal(a[0]), a[1]);
                } catch (Exception e) {
                    throw new CoercingParseLiteralException("Cannot parse [" + o + "] to MonetaryAmount. Expected format: 12.34 EUR", e);
                }
            }

            @Override
            public MonetaryAmount parseLiteral(Object o) throws CoercingParseLiteralException {
                if (o instanceof StringValue) {
                    String value = ((StringValue) o).getValue();
                    try {
                        return parseValue(value);
                    } catch (CoercingParseValueException e) {
                        throw new CoercingParseLiteralException("Cannot parse [" + value + "] to MonetaryAmount. Expected format: 12.34 EUR", e);
                    }
                } else {
                    throw new CoercingParseLiteralException(
                            "Expected literal of type StringValue but was " + o.getClass());
                }
            }
        });
    }
}