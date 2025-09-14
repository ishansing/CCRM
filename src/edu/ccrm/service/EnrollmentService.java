package edu.ccrm.service;

import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;

/**
 * This service manages the enrollment and unenrollment of students in courses.
 * It includes basic checks to ensure valid student and course objects are provided.
 */
public class EnrollmentService {

    /**
     * Attempts to enroll a student in a specific course.
     * We check for null inputs first, just to be safe. You never know what might come in!
     * @param student The student to enroll.
     * @param course The course to enroll the student in.
     * @return true if enrollment was successful, false otherwise.
     */
    public boolean enrollStudentInCourse(Student student, Course course) {
        if (student != null && course != null) {
            // This is where we could add more complex business rules, like checking max credits
            // or prerequisites. For now, it's pretty straightforward.
            student.enroll(course.getCourseCode());
            return true;
        }
        // If either the student or course object is null, we can't really do anything.
        return false;
    }

    /**
     * Attempts to unenroll a student from a specific course.
     * Again, a quick null check to prevent any unexpected issues.
     * @param student The student to unenroll.
     * @param course The course to unenroll the student from.
     * @return true if unenrollment was successful, false otherwise.
     */
    public boolean unenrollStudentFromCourse(Student student, Course course) {
        if (student != null && course != null) {
            student.unenroll(course.getCourseCode());
            return true;
        }
        return false;
    }
}
