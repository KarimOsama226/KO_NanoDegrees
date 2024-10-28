package com.udacity.jdnd.course3.critter.repository;// Repository for Customer
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDTO, Long> { }
