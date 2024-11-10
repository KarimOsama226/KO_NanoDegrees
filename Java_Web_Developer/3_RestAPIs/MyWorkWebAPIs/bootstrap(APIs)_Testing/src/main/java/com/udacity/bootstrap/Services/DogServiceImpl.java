package com.udacity.bootstrap.Services;

import com.udacity.bootstrap.Repository.DogRepository;
import com.udacity.bootstrap.Entity.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService{
    @Autowired
    DogRepository dogRepository;

    public List<Dog> retrieveDogs()
    {
        System.out.println("Hi from retrieveDogs");
        return (List<Dog>) dogRepository.findAll();
    }

    public List<String> retrieveDogBreed()
    {
        return (List<String>) dogRepository.findAllBreed();
    }

    public String retrieveDogBreedById(Long id)
    {
        Optional<String> optionalBreed = Optional.ofNullable(dogRepository.findBreedById(id));
        System.out.println("Hey, the ID = "+ id );
        return optionalBreed.orElseThrow(DogNotFoundException::new);
    }

    public List<String> retrieveDogNames()
    {
        return (List<String>)dogRepository.findAllName() ;
    }
}
