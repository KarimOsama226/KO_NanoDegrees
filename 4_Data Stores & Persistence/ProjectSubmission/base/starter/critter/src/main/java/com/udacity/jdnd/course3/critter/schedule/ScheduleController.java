package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.services.ScheduleService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

        private final ScheduleService scheduleService;

        @Autowired
        public ScheduleController(ScheduleService scheduleService) {
            this.scheduleService = scheduleService;
        }

        @PostMapping
        public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
            System.out.println("createSchedule");
            return scheduleService.createSchedule(scheduleDTO);
        }

        @GetMapping
        public List<ScheduleDTO> getAllSchedules() {
            System.out.println("getAllSchedules");
            return scheduleService.getAllSchedules();
        }

        @GetMapping("/pet/{petId}")
        public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
            System.out.println("getScheduleForPet");
            return scheduleService.getScheduleForPet(petId);
        }

        @GetMapping("/employee/{employeeId}")
        public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
            System.out.println("Hi there.......");
            return scheduleService.getScheduleForEmployee(employeeId);
        }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        if (customerId > 0) {
            List<ScheduleDTO> scheduleDTOs = scheduleService.getScheduleForCustomer(customerId); // Assume this returns List<ScheduleDTO>
            if (!scheduleDTOs.isEmpty()) {
                return scheduleDTOs;
            } else {
                throw new UnsupportedOperationException("Customer not found for Id " + customerId);
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    }