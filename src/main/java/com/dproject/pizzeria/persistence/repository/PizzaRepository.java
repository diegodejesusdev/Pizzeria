package com.dproject.pizzeria.persistence.repository;

import com.dproject.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailablePizzaTrueOrderByPricePizza();
    PizzaEntity findFirstByAvailablePizzaTrueAndNamePizzaIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailablePizzaTrueAndDescriptionPizzaContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailablePizzaTrueAndDescriptionPizzaNotContainingIgnoreCase(String description);
    List<PizzaEntity> findTop3ByAvailablePizzaTrueAndPricePizzaLessThanEqualOrderByPricePizzaAsc(double price);
    int countAllByVeganPizzaTrue();
}
