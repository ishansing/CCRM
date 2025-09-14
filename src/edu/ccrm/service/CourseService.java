package edu.ccrm.service;

import edu.ccrm.domain.Course;
import java.util.*;

/**
 * This service handles all operations related to courses, like adding new ones
 * or listing existing courses. It essentially manages our course catalog.
 */
public class CourseService {
    // I'm using a HashMap to store courses, with the course code as the key for quick lookups.
    private Map<String, Course> courseCatalog = new HashMap<>();

    /**
     * Adds a new course to the system. Before adding, it checks if a course with the same code
     * already exists to prevent duplicates. It's a good idea to avoid duplicate entries, right?
     * @param course The Course object to be added.
     */
    public void addCourse(Course course) {
        if (courseCatalog.containsKey(course.getCourseCode())) {
            System.out.println("Oops! A course with code '" + course.getCourseCode() + "' already exists. Not adding again.");
        } else {
            courseCatalog.put(course.getCourseCode(), course);
            System.out.println("Course '" + course.getCourseCode() + "' was successfully added.");
        }
    }

    /**
     * Retrieves a list of all courses currently in the system.
     * @return A List containing all Course objects.
     */
    public List<Course> listCourses() {
        return new ArrayList<>(courseCatalog.values());
    }
}
