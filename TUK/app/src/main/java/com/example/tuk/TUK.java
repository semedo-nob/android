package com.example.tuk;

public class TUK {
    // Class variables
    private String name;
    private String department;

    // Default private constructor
    private TUK() {
        // Default values or initialization logic can be added here
    }

    // Public constructor with parameters
    public TUK(String name, String department) {
        this.name = name;
        this.department = department;
    }

    // Getter for the name variable
    public String getName() {
        return name;
    }

    // Setter for the name variable
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the department variable
    public String getDepartment() {
        return department;
    }
}
