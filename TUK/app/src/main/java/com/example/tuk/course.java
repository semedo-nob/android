package com.example.tuk;
// A class to represent a course
public class Course {
    // A class variable to store the department of the course
    private static String department;

    // A class variable to store the name of the course
    private String name;

    // A private default constructor
    private Course() {
        // Do nothing
    }

    // A public constructor that takes the name of the course as a parameter
    public Course(String name) {
        // Set the name of the course
        this.name = name;
    }

    // A public getter for the name variable
    public String getName() {
        // Return the name of the course
        return this.name;
    }

    // A public setter for the name variable
    public void setName(String name) {
        // Set the name of the course
        this.name = name;
    }
}



