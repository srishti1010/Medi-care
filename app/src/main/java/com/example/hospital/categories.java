package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class categories extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private AutoCompleteTextView searchDoctor;
    private ArrayAdapter<String> adapter;
    private String[] suggestions = {
            "Cardiologist",
            "Dentist",
            "Ophthalmologist",
            "Pediatrician",
            "Gastroenterologist",
            "OB/GYN",
            "Medicine",
            "Neurologist",
            "Appointment"
    };

    // Layout item references
    private View cardiologistLayout;
    private View dentistLayout;
    private View eyeLayout;
    private View pregnantLayout;
    private View stomachLayout;
    private View allLayout;
    private View medicineLayout;
    private View neuroLayout;
    private View appointmentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        // Initialize BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Initialize AutoCompleteTextView
        searchDoctor = findViewById(R.id.search_doctor);

        // Initialize layout item references
        cardiologistLayout = findViewById(R.id.cardiologist_layout);
        dentistLayout = findViewById(R.id.dentist_layout);
        eyeLayout = findViewById(R.id.eye_layout);
        pregnantLayout = findViewById(R.id.pregnant_layout);
        stomachLayout = findViewById(R.id.stomach_layout);
        allLayout = findViewById(R.id.all_layout);
        medicineLayout = findViewById(R.id.medicine_layout);
        neuroLayout = findViewById(R.id.neuro_layout);
        appointmentLayout = findViewById(R.id.appointment_layout);

        // Set up ArrayAdapter with initial suggestions
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                suggestions
        );

        // Attach adapter to AutoCompleteTextView
        searchDoctor.setAdapter(adapter);

        // Handle item selection from the AutoCompleteTextView
        searchDoctor.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            Toast.makeText(categories.this, selectedItem + " selected", Toast.LENGTH_SHORT).show();
            updateVisibility(selectedItem);
        });

        // Set onClick listeners for layout items to navigate to the doctor_list_activity
        cardiologistLayout.setOnClickListener(v -> navigateToDoctorList("Cardiologist"));
        dentistLayout.setOnClickListener(v -> navigateToDoctorList("Dentist"));
        eyeLayout.setOnClickListener(v -> navigateToDoctorList("Ophthalmologist"));
        pregnantLayout.setOnClickListener(v -> navigateToDoctorList("Pediatrician"));
        stomachLayout.setOnClickListener(v -> navigateToDoctorList("Gastroenterologist"));
        allLayout.setOnClickListener(v -> navigateToDoctorList("OB/GYN"));
        medicineLayout.setOnClickListener(v -> navigateToDoctorList("Medicine"));
        neuroLayout.setOnClickListener(v -> navigateToDoctorList("Neurologist"));
        appointmentLayout.setOnClickListener(v -> navigateToDoctorList("Appointment"));

        // Handle BottomNavigationView item selection
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.tab_home) {
                Intent intent = new Intent(categories.this, home.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.tab_categories) {
                Intent intent = new Intent(categories.this, categories.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.tab_basket) {
                Intent intent = new Intent(categories.this, doctor_list_activity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.tab_profile) {
                Intent intent = new Intent(categories.this, profile.class);
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
    }

    // Method to navigate to doctor_list_activity with category name
    private void navigateToDoctorList(String category) {
        Intent intent = new Intent(categories.this, doctor_list_activity.class);
        intent.putExtra("doctor_category", category); // Pass category name to the next activity
        startActivity(intent);
    }

    private void updateVisibility(String selectedItem) {
        // Set all layouts to GONE
        cardiologistLayout.setVisibility(View.GONE);
        dentistLayout.setVisibility(View.GONE);
        eyeLayout.setVisibility(View.GONE);
        pregnantLayout.setVisibility(View.GONE);
        stomachLayout.setVisibility(View.GONE);
        allLayout.setVisibility(View.GONE);
        medicineLayout.setVisibility(View.GONE);
        neuroLayout.setVisibility(View.GONE);
        appointmentLayout.setVisibility(View.GONE);

        // Show only the selected item
        switch (selectedItem) {
            case "Cardiologist":
                cardiologistLayout.setVisibility(View.VISIBLE);
                break;
            case "Dentist":
                dentistLayout.setVisibility(View.VISIBLE);
                break;
            case "Ophthalmologist":
                eyeLayout.setVisibility(View.VISIBLE);
                break;
            case "Pediatrician":
                pregnantLayout.setVisibility(View.VISIBLE);
                break;
            case "Gastroenterologist":
                stomachLayout.setVisibility(View.VISIBLE);
                break;
            case "OB/GYN":
                allLayout.setVisibility(View.VISIBLE);
                break;
            case "Medicine":
                medicineLayout.setVisibility(View.VISIBLE);
                break;
            case "Neurologist":
                neuroLayout.setVisibility(View.VISIBLE);
                break;
            case "Appointment":
                appointmentLayout.setVisibility(View.VISIBLE);
                break;
            default:
                // No action if no valid item is selected
                break;
        }
    }
}
