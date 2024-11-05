package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }
    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return petService.savePet(petDTO);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return petService.findPetById(petId);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.findAllPets();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetsByOwnerId(ownerId);
    }
    @GetMapping("/{petId}/owner")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        return petService.findOwnerByPetId(petId);
    }
}
