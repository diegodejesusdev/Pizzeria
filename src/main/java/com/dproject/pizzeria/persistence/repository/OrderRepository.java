package com.dproject.pizzeria.persistence.repository;

import com.dproject.pizzeria.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByDateOrderAfter(LocalDateTime date);
    List<OrderEntity> findAllByMethodOrderIn(List<String> methodOrder);
}
