package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.*;

/**
 * This service manages all student-related operations, such as adding new students,
 * retrieving student information, and deactivating student accounts.
 */
public class StudentService {
    // I'm using a HashMap to store student records, with the registration number as the key.
    // This allows for really fast lookups, which is great for a system like this.
    private Map<String, Student> studentRecords = new HashMap<>();

    /**
     * Adds a new student to the system. It first checks if a student with the same
     * registration number already exists to prevent any accidental duplicates.
     * @param student The Student object to be added.
     */
    public void addStudent(Student student) {
        if (studentRecords.containsKey(student.getRegistrationNumber())) {
            System.out.println("Heads up! A student with registration number '" + student.getRegistrationNumber() + "' already exists. Skipping addition.");
        } else {
            studentRecords.put(student.getRegistrationNumber(), student);
            System.out.println("Student '" + student.getFullName() + "' (Reg. No: " + student.getRegistrationNumber() + ") was successfully added.");
        }
    }

    /**
     * Retrieves a student by their registration number.
     * @param registrationNumber The unique registration number of the student.
     * @return The Student object if found, otherwise null.
     */
    public Student getStudent(String registrationNumber) {
        return studentRecords.get(registrationNumber);
    }

    /**
     * Returns a list of all students currently in the system.
     * @return A List containing all Student objects.
     */
    public List<Student> listStudents() {
        return new ArrayList<>(studentRecords.values());
    }

    /**
     * Deactivates a student's account. This doesn't remove them from the system,
     * but marks them as inactive. Useful for students who have graduated or taken a leave of absence.
     * @param registrationNumber The registration number of the student to deactivate.
     */
    public void deactivateStudent(String registrationNumber) {
        Student studentToDeactivate = studentRecords.get(registrationNumber);
        if (studentToDeactivate != null) {
            studentToDeactivate.setActive(false);
            System.out.println("Student '" + registrationNumber + "' has been deactivated.");
        } else {
            System.out.println("Couldn't find student with registration number '" + registrationNumber + "' to deactivate.");
        }
    }
}
