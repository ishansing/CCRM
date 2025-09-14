package edu.ccrm.domain;

/**
 * Represents an instructor in the Campus Course & Records Manager system.
 * This class extends Person and includes instructor-specific details like their department.
 */
public class Instructor extends Person {
    private String department;

    public Instructor(String personId, String fullName, String email, String department) {
        super(personId, fullName, email);
        this.department = department;
    }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String getProfile() {
        // Just a simple profile for now, could be expanded later.
        return "Instructor [ID: " + getPersonId() + ", Name: " + getFullName() + ", Dept: " + department + "]";
    }
}
