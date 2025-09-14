package edu.ccrm.domain;

/**
 * Represents the possible grades a student can receive in a course.
 * Each grade has an associated point value, which is useful for GPA calculation.
 * This is a pretty standard grading scale, you know?
 */
public enum Grade {
    S(10), A(9), B(8), C(7), D(6), E(5), F(0);

    private final int points;
    Grade(int points) { this.points = points; }
    public int getPoints() { return points; }
}
