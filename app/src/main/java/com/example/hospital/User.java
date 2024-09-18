package com.example.hospital;

public class User {
    private String name;
    private String fatherName;
    private String motherName;
    private String age;
    private String email;
    private String phone;
    private String bloodGroup;
    private String kids;
    private String diseaseHistory; // Add this field

    // Default constructor required for Firebase
    public User() {
    }

    // Constructor with all parameters
    public User(String name, String fatherName, String motherName, String age, String email, String phone, String bloodGroup, String kids, String diseaseHistory) {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
        this.kids = kids;
        this.diseaseHistory = diseaseHistory; // Initialize this field
    }

    // Getters and setters for each field
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public String getMotherName() { return motherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getKids() { return kids; }
    public void setKids(String kids) { this.kids = kids; }

    public String getDiseaseHistory() { return diseaseHistory; } // Add this method
    public void setDiseaseHistory(String diseaseHistory) { this.diseaseHistory = diseaseHistory; } // Add this method
}
