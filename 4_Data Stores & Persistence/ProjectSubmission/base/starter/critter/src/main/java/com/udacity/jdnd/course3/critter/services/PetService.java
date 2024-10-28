package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public PetDTO savePet(PetDTO pet) {
        return petRepository.save(pet);
    }

    public PetDTO findPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    public List<PetDTO> findAllPets() {
        return petRepository.findAll();
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
    public List<PetDTO> getPetsByOwnerId(long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }
    public List<PetDTO> findPetsByOwnerId(long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    public CustomerDTO findOwnerByPetId(long petId) {
        PetDTO pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        return customerRepository.findById(pet.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }
}
