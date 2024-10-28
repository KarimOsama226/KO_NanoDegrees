package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final PetRepository petRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, PetRepository petRepository) {
        this.scheduleRepository = scheduleRepository;
        this.petRepository = petRepository;
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
        List<PetDTO> petIds = petRepository.findPetsByOwnerId(ownerId);
        List<ScheduleDTO> schedules = new ArrayList<>();
        for (PetDTO petId : petIds) {
            schedules.addAll(scheduleRepository.findByPetIds(petId.getId()));
        }
        return schedules;
    }
}
