package com.dproject.pizzeria.persistence.repository;

import com.dproject.pizzeria.persistence.entity.PizzaEntity;
import com.dproject.pizzeria.services.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailablePizzaTrueOrderByPricePizza();
    PizzaEntity findFirstByAvailablePizzaTrueAndNamePizzaIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailablePizzaTrueAndDescriptionPizzaContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailablePizzaTrueAndDescriptionPizzaNotContainingIgnoreCase(String description);
    List<PizzaEntity> findTop3ByAvailablePizzaTrueAndPricePizzaLessThanEqualOrderByPricePizzaAsc(double price);
    int countAllByVeganPizzaTrue();

    @Query(value = "UPDATE pizza SET price_pizza = :#{#newPizzaPrice.newPrice} WHERE id_pizza = :#{#newPizzaPrice.idPizza}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice")UpdatePizzaPriceDto newPizzaPrice);
}
