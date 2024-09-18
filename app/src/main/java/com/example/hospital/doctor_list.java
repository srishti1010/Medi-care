package com.example.hospital;

import java.util.ArrayList;
import java.util.List;

public class doctor_list {

    public static List<doc> getDoctors() {
        List<doc> doctors = new ArrayList<>();

        // Add some dummy doctor data with drawable resource IDs
        doctors.add(new doc(
                "1", // Unique ID for the doctor
                "Dr. Emily Smith",
                "emily.smith@example.com",
                "Cardiologist",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                R.drawable.doc1, // Drawable resource ID
                4.5f
        ));

        doctors.add(new doc(
                "2", // Unique ID for the doctor
                "Dr. John Doe",
                "john.doe@example.com",
                "Pediatrician",
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                R.drawable.doc2, // Drawable resource ID
                4.7f
        ));

        doctors.add(new doc(
                "3", // Unique ID for the doctor
                "Dr. Sarah Johnson",
                "sarah.johnson@example.com",
                "Dermatologist",
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.doc3, // Drawable resource ID
                4.3f
        ));

        doctors.add(new doc(
                "4", // Unique ID for the doctor
                "Dr. Mark Wilson",
                "mark.wilson@example.com",
                "Neurologist",
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                R.drawable.doc4, // Drawable resource ID
                4.8f
        ));

        doctors.add(new doc(
                "5", // Unique ID for the doctor
                "Dr. Jessica Brown",
                "jessica.brown@example.com",
                "Orthopedic Surgeon",
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                R.drawable.doc1, // Ensure this drawable exists
                4.6f
        ));

        return doctors;
    }
}
