package com.dproject.pizzeria.persistence.repository;

import com.dproject.pizzeria.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, Integer> {
    @Query(value = "select c from CustomerEntity c where c.phoneCustomer = :phone")
    CustomerEntity findByPhoneCustomer(@Param("phone") String phone);
}
