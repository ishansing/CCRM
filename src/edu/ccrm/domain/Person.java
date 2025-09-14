package edu.ccrm.domain;

import java.time.LocalDate;

/**
 * This is a base class for any person in our system, like a student or an instructor.
 * It holds the common properties that all people share.
 */
public abstract class Person {
    protected String personId;
    protected String fullName;
    protected String email;
    protected boolean active;
    protected LocalDate createdAt;

    public Person(String personId, String fullName, String email) {
        this.personId = personId;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = LocalDate.now();
        this.active = true;
    }

    public abstract String getProfile();

    // Standard getters and setters below
    public String getPersonId() { return personId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public boolean isActive() { return active; }
    public LocalDate getCreatedAt() { return createdAt; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setActive(boolean active) { this.active = active; }

    // I'm overriding toString to give a nice summary of the person.
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Email: %s, Active: %b, Since: %s",
            personId, fullName, email, active, createdAt);
    }
}