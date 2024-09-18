package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity {

    private ImageView hospitalLogo, backgroundImage, hotlineTextView, additionalImage, myAccountButton;
    private Button patientAccessButton, appointmentButton;
    private CardView findDoctorCard, testResultsCard, onlineAdmissionCard;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        // Initialize views
        hospitalLogo = findViewById(R.id.hospital_logo);
        backgroundImage = findViewById(R.id.background_image);
        additionalImage = findViewById(R.id.additional_image);
        myAccountButton = findViewById(R.id.cart_icon); // Initialize the account button
        appointmentButton = findViewById(R.id.appointment_button);

        patientAccessButton = findViewById(R.id.patient_access_button);
        hotlineTextView = findViewById(R.id.medicine_icon); // This represents the hotlineTextView (medicine icon)
        findDoctorCard = findViewById(R.id.find_doctor_card);
        testResultsCard = findViewById(R.id.test_results_card);
        onlineAdmissionCard = findViewById(R.id.online_admission_card);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set click listeners for buttons and cards
        myAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(home.this, CartActivity.class); // Navigate to the CartActivity
            startActivity(intent);
        });

        appointmentButton.setOnClickListener(v ->
                {
                    Intent intent = new Intent(home.this, BookAppointmentActivity.class); // Navigate to the CartActivity
                    startActivity(intent);
                }
        );

        patientAccessButton.setOnClickListener(v -> {
            Intent intent = new Intent(home.this, UserDetailActivity.class); // Navigate to the UserDetailActivity
            startActivity(intent);
        });

        findDoctorCard.setOnClickListener(v ->
                Toast.makeText(home.this, "Find Doctor Card Clicked", Toast.LENGTH_SHORT).show()
        );

        testResultsCard.setOnClickListener(v ->
                Toast.makeText(home.this, "Test Results Card Clicked", Toast.LENGTH_SHORT).show()
        );

        onlineAdmissionCard.setOnClickListener(v ->
                Toast.makeText(home.this, "Online Admission Card Clicked", Toast.LENGTH_SHORT).show()
        );

        // Navigate to the medicine page when hotlineTextView is clicked
        hotlineTextView.setOnClickListener(v -> {
            Intent intent = new Intent(home.this, MedicineActivity.class); // Replace 'MedicineActivity' with the actual medicine page activity
            startActivity(intent);
        });

        // Set the OnNavigationItemSelectedListener for the bottom navigation bar
        bottomNavigationView.setOnItemSelectedListener(item -> {
            // Use if-else to handle the navigation item click
            if (item.getItemId() == R.id.tab_home) {
                Toast.makeText(home.this, "Home Clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.tab_categories) {
                Intent intent = new Intent(home.this, categories.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.tab_basket) {
                Intent intent = new Intent(home.this, doctor_list_activity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.tab_profile) {
                Intent intent = new Intent(home.this, profile.class);
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
    }
}
