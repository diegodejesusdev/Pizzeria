package com.dproject.pizzeria.persistence.repository;

import com.dproject.pizzeria.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {

}
