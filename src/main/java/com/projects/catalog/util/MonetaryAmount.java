package com.projects.catalog.util;

import java.math.BigDecimal;

public record MonetaryAmount(BigDecimal amount, String currency) {

}
