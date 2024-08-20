package com.dproject.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Pizza")
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza",nullable = false)
    private Integer idPizza;
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean availablePizza;
    @Column(nullable = false, length = 30, unique = true)
    private String namePizza;
    @Column(nullable = false, length = 150)
    private String descriptionPizza;
    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double pricePizza;
    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarianPizza;
    @Column(columnDefinition = "TINYINT")
    private Boolean veganPizza;

}
