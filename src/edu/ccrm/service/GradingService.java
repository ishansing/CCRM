package edu.ccrm.service;

import edu.ccrm.domain.*;

/**
 * This service is responsible for all grading-related operations,
 * including calculating grades from marks and computing a student's GPA.
 */
public class GradingService {

    /**
     * Calculates the letter grade based on the provided marks.
     * This is a pretty standard grading scale, nothing too fancy here.
     * @param marks The numerical marks obtained by the student.
     * @return The corresponding Grade enum value.
     */
    public static Grade calculateGrade(int marks) {
        if (marks >= 90) return Grade.S;
        else if (marks >= 80) return Grade.A;
        else if (marks >= 70) return Grade.B;
        else if (marks >= 60) return Grade.C;
        else if (marks >= 50) return Grade.D;
        else if (marks >= 40) return Grade.E;
        else return Grade.F;
    }

    /**
     * Assigns marks and a calculated grade to a student for a specific course.
     * This also adds an entry to the student's academic transcript.
     * @param student The student to assign the marks to.
     * @param courseCode The code of the course.
     * @param marks The marks obtained.
     */
    public void assignMarks(Student student, String courseCode, int marks) {
        Grade grade = calculateGrade(marks);
        student.addTranscriptEntry(courseCode, marks, grade);
    }

    /**
     * Computes the Grade Point Average (GPA) for a given student.
     * It iterates through all transcript entries and sums up the grade points.
     * If a student has no transcript entries, their GPA is naturally 0.0.
     * @param student The student for whom to compute the GPA.
     * @return The calculated GPA as a double.
     */
    public double computeGPA(Student student) {
        if (student.getAcademicTranscript().isEmpty()) return 0.0; // Can't divide by zero, so handle this edge case.

        double totalPoints = 0;
        int numberOfCourses = 0;
        for (TranscriptEntry transcriptEntry : student.getAcademicTranscript().values()) {
            totalPoints += transcriptEntry.getGrade().getPoints();
            numberOfCourses++;
        }
        // Just a quick check to prevent division by zero, though the isEmpty check above should catch most cases.
        return (numberOfCourses == 0) ? 0.0 : totalPoints / numberOfCourses;
    }
}
