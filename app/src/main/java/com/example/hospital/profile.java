package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class profile extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile); // Ensure the layout file name is correct

        // Initialize BottomNavigationView and set listener
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.tab_home) {
                startActivity(new Intent(profile.this, home.class));
                return true;
            } else if (itemId == R.id.tab_categories) {
                startActivity(new Intent(profile.this, categories.class));
                return true;
            } else if (itemId == R.id.tab_basket) {
                startActivity(new Intent(profile.this, doctor_list_activity.class));
                return true;
            } else if (itemId == R.id.tab_profile) {
                // Already in profile activity, so no need to start a new instance
                return true;
            }
            return false;
        });

        // Set up click listeners for clickable views
        findViewById(R.id.profile_image).setOnClickListener(this::onViewClicked);
        findViewById(R.id.per).setOnClickListener(this::onViewClicked);
        findViewById(R.id.appoint).setOnClickListener(this::onViewClicked);
        findViewById(R.id.pay).setOnClickListener(this::onViewClicked);
        findViewById(R.id.report).setOnClickListener(this::onViewClicked);
    }

    private void onViewClicked(View view) {
        int id = view.getId();

        if (id == R.id.per) {
            showToast("Personal Details clicked");
            // Start PersonalDetailActivity
            Intent intent = new Intent(profile.this, PersonalDetailActivity.class);
            startActivity(intent);
        } else if (id == R.id.appoint) {
            showToast("Appointment clicked");
            Intent intent = new Intent(profile.this, AppointmentViewActivity.class);
            startActivity(intent);
            // Start new activity or perform action
        } else if (id == R.id.pay) {
            showToast("Payments clicked");
            Intent intent = new Intent(profile.this, PaymentActivity.class);
            startActivity(intent);
            // Start new activity or perform action
        } else if (id == R.id.report) {
            showToast("Hospital Information clicked");
            Intent intent = new Intent(profile.this, HospitalInfoActivity.class);
            startActivity(intent);
            // Start new activity or perform action
        } else {
            showToast("Unknown view clicked");
        }
    }

    private void showToast(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
