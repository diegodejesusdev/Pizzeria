package com.dproject.pizzeria.persistence.entity;

import com.dproject.pizzeria.persistence.audit.AuditPizzaListener;
import com.dproject.pizzeria.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table(name = "Pizza")
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity implements Serializable {
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

    @Override
    public String toString() {
        return "PizzaEntity{" +
                "availablePizza=" + availablePizza +
                ", idPizza=" + idPizza +
                ", namePizza='" + namePizza + '\'' +
                ", descriptionPizza='" + descriptionPizza + '\'' +
                ", pricePizza=" + pricePizza +
                ", vegetarianPizza=" + vegetarianPizza +
                ", veganPizza=" + veganPizza +
                '}';
    }
}
