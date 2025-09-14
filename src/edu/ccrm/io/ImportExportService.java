package edu.ccrm.io;

import edu.ccrm.domain.*;
import edu.ccrm.service.*;

import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;

/**
 * This service handles the import and export of student and course data
 * to and from CSV files. It's pretty handy for getting data in and out of the system.
 */
public class ImportExportService {

    /**
     * Imports student data from a specified CSV file.
     * The CSV format expected is: personId,registrationNumber,fullName,email.
     * Each line is processed, and a new Student object is created and added to the student service.
     * @param filePath The path to the CSV file.
     * @param studentService The service to add the imported students to.
     * @return The number of students successfully imported.
     * @throws IOException If there's an issue reading the file.
     */
    public int importStudents(String filePath, StudentService studentService) throws IOException {
        List<String> csvLines = Files.readAllLines(Paths.get(filePath));
        int importedCount = 0;
        // Skipping the header line if it exists, or just processing all lines.
        for (String line : csvLines) {
            String[] lineParts = line.strip().split(",");
            // Just a quick check to make sure we have enough data points in the line.
            if (lineParts.length >= 4) {
                Student student = new Student(lineParts[0], lineParts[1], lineParts[2], lineParts[3]);
                studentService.addStudent(student);
                importedCount++;
            }
        }
        return importedCount;
    }

    /**
     * Exports all current student data from the system to a specified CSV file.
     * The output format will be: personId,registrationNumber,fullName,email.
     * @param filePath The path where the CSV file should be saved.
     * @param studentService The service to retrieve student data from.
     * @throws IOException If there's an issue writing to the file.
     */
    public void exportStudents(String filePath, StudentService studentService) throws IOException {
        List<String> studentCsvLines = studentService.listStudents()
            .stream()
            // Mapping each student object to a CSV formatted string.
            .map(student -> student.getPersonId() + "," + student.getRegistrationNumber() + "," + student.getFullName() + "," + student.getEmail())
            .collect(Collectors.toList());
        Files.write(Paths.get(filePath), studentCsvLines);
        System.out.println("Student data exported to: " + filePath);
    }

    /**
     * Imports course data from a specified CSV file.
     * The CSV format expected is: courseCode,title,credits,instructorPersonId,semester,department.
     * @param filePath The path to the CSV file.
     * @param courseService The service to add the imported courses to.
     * @return The number of courses successfully imported.
     * @throws IOException If there's an issue reading the file.
     */
    public int importCourses(String filePath, CourseService courseService) throws IOException {
        List<String> csvLines = Files.readAllLines(Paths.get(filePath));
        int importedCount = 0;
        for (String line : csvLines) {
            String[] lineParts = line.strip().split(",");
            if (lineParts.length >= 6) {
                Course course = new Course(
                    lineParts[0], // courseCode
                    lineParts[1], // title
                    Integer.parseInt(lineParts[2]), // credits - remember to parse this as an integer!
                    lineParts[3], // instructorPersonId
                    Semester.valueOf(lineParts[4].toUpperCase()), // Convert string to enum, case-insensitively.
                    lineParts[5] // department
                );
                courseService.addCourse(course);
                importedCount++;
            }
        }
        return importedCount;
    }

    /**
     * Exports all current course data from the system to a specified CSV file.
     * The output format will be: courseCode,title,credits,instructorPersonId,semester,department.
     * @param filePath The path where the CSV file should be saved.
     * @param courseService The service to retrieve course data from.
     * @throws IOException If there's an issue writing to the file.
     */
    public void exportCourses(String filePath, CourseService courseService) throws IOException {
        List<String> courseCsvLines = courseService.listCourses()
            .stream()
            // Mapping each course object to a CSV formatted string.
            .map(course -> course.getCourseCode() + "," + course.getTitle() + "," + course.getCredits() + "," +
                      course.getInstructorPersonId() + "," + course.getSemester() + "," + course.getDepartment())
            .collect(Collectors.toList());
        Files.write(Paths.get(filePath), courseCsvLines);
        System.out.println("Course data exported to: " + filePath);
    }
}
