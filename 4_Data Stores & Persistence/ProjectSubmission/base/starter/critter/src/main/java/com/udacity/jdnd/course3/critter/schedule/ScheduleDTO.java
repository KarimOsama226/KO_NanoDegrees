package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@Entity
@Table(name = "schedule")
public class ScheduleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ElementCollection
    @CollectionTable(name = "schedule_employee_ids", joinColumns = @JoinColumn(name = "schedule_id"))
    private List<Long> employeeIds;
    @ElementCollection
    @CollectionTable(name = "schedule_pet_ids", joinColumns = @JoinColumn(name = "schedule_id"))
    private List<Long> petIds;
    private LocalDate date;
    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)  // This ensures that EmployeeSkill is stored as a string in the database
    @CollectionTable(name = "schedule_activities", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "activity")
    private Set<EmployeeSkill> activities;

    public long getId(){
        return id;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
