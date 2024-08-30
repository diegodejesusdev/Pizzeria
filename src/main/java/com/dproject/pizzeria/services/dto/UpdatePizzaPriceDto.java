package com.dproject.pizzeria.services.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDto {
    private Integer idPizza;
    private double newPrice;
}
