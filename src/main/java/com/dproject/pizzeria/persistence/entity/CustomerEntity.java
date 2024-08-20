package com.dproject.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Setter
@Getter
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer",nullable = false)
    private Integer idCustomer;
    @Column(nullable = false, length = 60)
    private String nameCustomer;
    @Column(nullable = false, length = 100)
    private String addressCustomer;
    @Column(nullable = false, length = 50)
    private String emailCustomer;
    @Column(nullable = false, length = 20)
    private String phoneCustomer;
}
