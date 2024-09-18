package com.example.hospital;


public class SimpleDoctor {
    private String name;
    private String specialization;

    // Constructor
    public SimpleDoctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }
}

