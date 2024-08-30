package com.dproject.pizzeria.web.controller;

import com.dproject.pizzeria.persistence.entity.PizzaEntity;
import com.dproject.pizzeria.services.PizzaService;
import com.dproject.pizzeria.services.dto.UpdatePizzaPriceDto;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(pizzaService.getAll(page, elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable Integer idPizza){
        return ResponseEntity.ok(pizzaService.getById(idPizza));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int elements,
            @RequestParam(defaultValue = "pricePizza") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(pizzaService.getAvailable(page, elements, sortBy, sortDirection));
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

    @PutMapping("/update_price/")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto){
        if (this.pizzaService.ifExits(dto.getIdPizza())){
            this.pizzaService.updatePrice(dto);
            return ResponseEntity.ok().build();
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
