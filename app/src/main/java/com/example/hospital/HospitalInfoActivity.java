package com.example.hospital;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HospitalInfoActivity extends AppCompatActivity {

    private ListView newMachinesListView;
    private String[] newMachines = {"MRI Scanner", "CT Scanner", "Ultrasound Machine", "X-Ray Machine"};
    private RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Setup OnBackPressedDispatcher for handling system back button press
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish(); // Close the activity and go back
            }
        });
        // Set up Toolbar
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//            if (getSupportActionBar() != null) {
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            }
//        } else {
//            Log.e("HospitalInfoActivity", "Toolbar is not found");
//        }

        // Set up ListView for new machines
        newMachinesListView = findViewById(R.id.new_machines_list);
        if (newMachinesListView != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, newMachines);
            newMachinesListView.setAdapter(adapter);
        } else {
            Log.e("HospitalInfoActivity", "ListView is not found");
        }

        // Set up other views
        TextView hospitalName = findViewById(R.id.hospital_name);
        TextView hospitalQualities = findViewById(R.id.hospital_qualities);
        TextView hospitalAddress = findViewById(R.id.hospital_address);
        TextView hospitalEmail = findViewById(R.id.hospital_email);
        TextView hospitalPhone = findViewById(R.id.hospital_phone);
        ratingBar = findViewById(R.id.hospital_rating);
        Button moreInfoButton = findViewById(R.id.more_info_button);
        TextView reviewsHeading = findViewById(R.id.hospital_reviews_heading);
        TextView reviewsText = findViewById(R.id.hospital_reviews);

        if (hospitalName != null && hospitalQualities != null && hospitalAddress != null
                && hospitalEmail != null && hospitalPhone != null && ratingBar != null
                && moreInfoButton != null && reviewsHeading != null && reviewsText != null) {
            hospitalName.setText("Super Care Hospital");
            hospitalQualities.setText("Qualities:\n- High quality care\n- Experienced staff\n- Modern facilities");
            hospitalAddress.setText("Address: 123 Hospital Lane, City, Country");
            hospitalEmail.setText("Email: contact@hospital.com");
            hospitalPhone.setText("Phone: +123456789");
            ratingBar.setRating(4.5f); // Set initial rating
            reviewsHeading.setText("Hospital Reviews");
            reviewsText.setText("Review 1: Excellent service.\nReview 2: Friendly staff and clean facilities.");

            // Set up button click listener
            moreInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle button click
                    // Example: Open a new activity or show a dialog with more information
                }
            });
        } else {
            Log.e("HospitalInfoActivity", "One or more views are not found");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle the back button
                finish(); // Closes the current activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
