package com.udacity.bootstrap.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.bootstrap.Entity.Dog;
import com.udacity.bootstrap.Repository.DogRepository;
import com.udacity.bootstrap.Services.DogNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class Mutation implements GraphQLMutationResolver {
    DogRepository dogRepository;
    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }
    public Boolean deleteDogBreed (Long id)
    {
        dogRepository.deleteById(id);
        return true;
    }
    public Dog updateDogName (String name,Long id)
    {
        Optional <Dog> optionalDog = dogRepository.findById(id);
        System.out.println("ID= " + id + "Name= " + name);
        if(optionalDog.isPresent())
        {
            Dog dog = optionalDog.get();
            dog.setName(name);
            dogRepository.save(dog);
            return dog;
        }
        else
        {
            throw new DogNotFoundException("ID of that Dog is not Found",id);
        }

    }
}
