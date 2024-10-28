package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleDTO, Long> {

    @Query("SELECT s FROM ScheduleDTO s JOIN s.petIds p WHERE p = :petId")
    List<ScheduleDTO> findByPetIds(Long petId);

    @Query("SELECT s FROM ScheduleDTO s JOIN s.employeeIds e WHERE e = :employeeId")
    List<ScheduleDTO> findByEmployeeIds(Long employeeId);
}
