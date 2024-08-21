package com.dproject.pizzeria.services;

import com.dproject.pizzeria.persistence.entity.PizzaEntity;
import com.dproject.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.dproject.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaSortRepository = pizzaSortRepository;
    }

    public Page<PizzaEntity> getAll(int page, int elements){
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaSortRepository.findAll(pageRequest);
    }

    public PizzaEntity getById(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public boolean ifExits(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }

    public void deleteById(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }

    public Page<PizzaEntity> getAvailable(int page, int elements, String shortBy, String shortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(shortDirection), shortBy);
        Pageable pageable = PageRequest.of(page, elements, sort);
        return this.pizzaSortRepository.findByAvailablePizzaTrue(pageable);
    }

    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findFirstByAvailablePizzaTrueAndNamePizzaIgnoreCase(name);
    }

    public List<PizzaEntity> getByIngredient(String ingredient){
        return this.pizzaRepository.findAllByAvailablePizzaTrueAndDescriptionPizzaContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getByNotIngredient(String notIngredient){
        return this.pizzaRepository.findAllByAvailablePizzaTrueAndDescriptionPizzaNotContainingIgnoreCase(notIngredient);
    }

    public List<PizzaEntity> getCheapest(double price){
        return this.pizzaRepository.findTop3ByAvailablePizzaTrueAndPricePizzaLessThanEqualOrderByPricePizzaAsc(price);
    }
}
