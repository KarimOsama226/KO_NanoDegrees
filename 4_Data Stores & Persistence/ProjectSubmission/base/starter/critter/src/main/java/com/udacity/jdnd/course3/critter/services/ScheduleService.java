package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final PetRepository petRepository;
    private final PetService petService;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, PetRepository petRepository, PetService petService) {
        this.scheduleRepository = scheduleRepository;
        this.petRepository = petRepository;
        this.petService = petService;
    }

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        return scheduleRepository.save(scheduleDTO);
    }

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleDTO> getScheduleForPet(long petId) {
        return scheduleRepository.findByPetIds(petId);
    }

    public List<ScheduleDTO> getScheduleForEmployee(long employeeId) {
        return scheduleRepository.findByEmployeeIds(employeeId);
    }

    public List<ScheduleDTO> getScheduleForCustomer(long ownerId) {
//        List<PetDTO> petIds = petRepository.findByOwnerId(ownerId);
        List<PetDTO> petIds = petService.getPetsByOwnerId(ownerId);
        System.out.println("================================================List of Pets: " + petIds.get(0).getName());
        System.out.println("================================================List of Pets: " + petIds.get(0).getId());
        List<ScheduleDTO> schedules = new ArrayList<>();
        System.out.println("List of Pet IDs: " + petIds.stream().map(PetDTO::getId).collect(Collectors.toList()));
        for (PetDTO petId : petIds) {
            System.out.println("Looking up schedules for Pet ID: " + petId.getId());
            List<ScheduleDTO> foundSchedules = scheduleRepository.findByPetIds(petId.getId());
            System.out.println("Schedules found for Pet ID " + petId.getId() + ": " + foundSchedules);
            schedules.addAll(foundSchedules);
        }

        return schedules;
    }
//    public List<ScheduleDTO> getScheduleByCust(long customerId) {
//        List<PetDTO> pets = petRepository.findByOwnerId(customerId);
//        List<ScheduleDTO> schedules = new ArrayList<>();
//        pets.forEach(pet -> {
//            List<ScheduleDTO> petsSchedules = getScheduleForPet(pet.getId());
//            schedules.addAll(petsSchedules);
//        });
//        return schedules;
//    }

}
