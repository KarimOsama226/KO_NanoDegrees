package com.udacity.jdnd.course3.critter.repository;// Repository for Pet
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<PetDTO, Long> {
    List<PetDTO> findByOwnerId(Long ownerId);
    @Query(value = "SELECT * FROM pets p WHERE p.owner_id = :ownerId", nativeQuery = true)
    List<PetDTO> findPetsByOwnerId(@Param("ownerId") Long ownerId);
    @Query("SELECT s FROM ScheduleDTO s JOIN s.petIds p WHERE p = :petId")
    List<ScheduleDTO> findByPetIds(@Param("petId") Long petId);

}
