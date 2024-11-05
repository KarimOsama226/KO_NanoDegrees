package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetDTO;

import javax.persistence.*;
import java.util.List;
@Entity
/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Table(name = "customers")
public class CustomerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")  // Points to the "owner_id" in PetDTO
    private List<PetDTO> pets;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<PetDTO> getPetIds() {
        return pets;
    }

    public void setPetIds(List<PetDTO> pets) {
        this.pets = pets;
    }
}
