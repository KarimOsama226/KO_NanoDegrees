package com.udacity.eureka.Repo;

import com.udacity.eureka.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog,Long> {

}
