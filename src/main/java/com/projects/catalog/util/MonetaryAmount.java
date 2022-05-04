package com.projects.catalog.util;

import java.math.BigDecimal;

public class MonetaryAmount {

  private final BigDecimal amount;
  private final String currency;

  public MonetaryAmount(BigDecimal amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  @Override
  public String toString() {
    return amount + " " + currency;
  }
}
