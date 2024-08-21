package com.dproject.pizzeria.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {
    Integer getIdOrder();
    String getNameCustomer();
    LocalDateTime getDateOrder();
    Double getTotalOrder();
    String getNamePizza();
}
