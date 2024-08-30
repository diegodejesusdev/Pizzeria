package com.dproject.pizzeria.persistence.audit;

import com.dproject.pizzeria.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {

    private PizzaEntity currentPizza;

    @PostLoad
    public void postLoad(PizzaEntity pizza) {
        System.out.println("POST LOAD");
        this.currentPizza = SerializationUtils.clone(pizza);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizza) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + this.currentPizza);
        System.out.println("NEW VALUE: " + pizza.toString());
    }

    @PreRemove
    public void onPreDelete(PizzaEntity pizza) {
        System.out.println(pizza.toString());
    }
}
