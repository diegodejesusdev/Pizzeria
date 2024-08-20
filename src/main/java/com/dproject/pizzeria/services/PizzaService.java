package com.dproject.pizzeria.services;

import com.dproject.pizzeria.persistence.entity.PizzaEntity;
import com.dproject.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll(){
        System.out.println(this.pizzaRepository.countAllByVeganPizzaTrue());
        return this.pizzaRepository.findAll();
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

    public List<PizzaEntity> getAvailable(){
        return this.pizzaRepository.findAllByAvailablePizzaTrueOrderByPricePizza();
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
