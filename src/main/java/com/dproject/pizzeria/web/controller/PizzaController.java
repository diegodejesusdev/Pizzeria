package com.dproject.pizzeria.web.controller;

import com.dproject.pizzeria.persistence.entity.PizzaEntity;
import com.dproject.pizzeria.services.PizzaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {
    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok(pizzaService.getAll());
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable Integer idPizza){
        return ResponseEntity.ok(pizzaService.getById(idPizza));
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable(){
        return ResponseEntity.ok(pizzaService.getAvailable());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name){
        return ResponseEntity.ok(pizzaService.getByName(name));
    }

    @GetMapping("/ingredient/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByIngredient (@PathVariable String ingredient){
        return ResponseEntity.ok(pizzaService.getByIngredient(ingredient));
    }

    @GetMapping("/not_ingredient/{not_ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByNotIngredient (@PathVariable String not_ingredient){
        return ResponseEntity.ok(pizzaService.getByNotIngredient(not_ingredient));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable double price){
        return ResponseEntity.ok(pizzaService.getCheapest(price));
    }

    @PostMapping("/save")
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity Pizza){
        if (Pizza.getIdPizza() == null || !this.pizzaService.ifExits(Pizza.getIdPizza())){
            return ResponseEntity.ok(pizzaService.save(Pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/save")
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity Pizza){
        if (Pizza.getIdPizza() != null && this.pizzaService.ifExits(Pizza.getIdPizza())){
            return ResponseEntity.ok(pizzaService.save(Pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable Integer idPizza){
        if (this.pizzaService.ifExits(idPizza)){
            this.pizzaService.deleteById(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
