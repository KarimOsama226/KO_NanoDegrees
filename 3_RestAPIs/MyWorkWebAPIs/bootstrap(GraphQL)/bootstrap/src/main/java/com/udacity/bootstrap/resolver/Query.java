package com.udacity.bootstrap.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udacity.bootstrap.Entity.Dog;
import com.udacity.bootstrap.Repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class Query implements GraphQLQueryResolver {
    private DogRepository dogRepository;
    public Query (DogRepository dogRepository)
    {
        this.dogRepository = dogRepository;
    }
    /* Implements the Queries here*/
    public Iterable<Dog> findDogBreeds()
    {
        return dogRepository.findAll();
    }
    public Iterable<Dog> findDogBreedById (Long id)
    {
        return dogRepository.findAllById(Collections.singleton(id));
    }
    public Iterable<String> findAllDogNames ()
    {
        return dogRepository.findAllName();
    }
}
