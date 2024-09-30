package com.microservice.microservice.repository;

import com.microservice.microservice.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {

}
