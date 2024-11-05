package com.udacity.jdnd.course3.critter.services;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employee) {
        return employeeRepository.save(employee);
    }

    public EmployeeDTO findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<EmployeeDTO> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    public void setAvailability(long employeeId, Set<DayOfWeek> daysAvailable) {
        EmployeeDTO employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + employeeId));

        // Assuming Employee has a method to set availability
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }
    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO request) {
        DayOfWeek dayOfWeek = request.getDate().getDayOfWeek();
        Set<EmployeeDTO> qualifiedEmployees = new HashSet<>();

        for (EmployeeSkill skill : request.getSkills()) {
            List<EmployeeDTO> employees = employeeRepository.findEmployeesBySkillsOrAvailability(skill, dayOfWeek);
            qualifiedEmployees.addAll(employees);
        }
        return new ArrayList<>(qualifiedEmployees);
    }
}
