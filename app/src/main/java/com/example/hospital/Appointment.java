package com.example.hospital;

public class Appointment {
    private String id; // Add the id field
    private String date;
    private String doctorName;
    private String patientName;
    private String specialization;
    private String time;

    // Default constructor required for calls to DataSnapshot.getValue(Appointment.class)
    public Appointment() {
    }

    // Constructor with all fields including id
    public Appointment(String id, String date, String doctorName, String patientName, String specialization, String time) {
        this.id = id;
        this.date = date;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.specialization = specialization;
        this.time = time;
    }

    // Getter and setter for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getters and setters for other fields
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' + // Include id in the toString method
                ", date='" + date + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
