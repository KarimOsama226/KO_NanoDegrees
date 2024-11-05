package com.udacity.jdnd.course3.critter.repository;// Repository for Employee
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Long> {
    @Query("SELECT e FROM EmployeeDTO e " +
            "JOIN e.skills s " +
            "JOIN e.daysAvailable d " +
            "WHERE s = :skill OR d = :dayOfWeek")
    List<EmployeeDTO> findEmployeesBySkillsOrAvailability(
            @Param("skill") EmployeeSkill skill,
            @Param("dayOfWeek") DayOfWeek dayOfWeek);

}
