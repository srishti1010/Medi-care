package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class doctordesc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_description); // Ensure this matches your actual layout resource

        // Initialize BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set up BottomNavigationView listener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle item selection with if-else
                int itemId = item.getItemId();

                if (itemId == R.id.tab_home) {
                    Intent intent = new Intent(doctordesc.this, home.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.tab_categories) {
                    Intent intent = new Intent(doctordesc.this, categories.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.tab_basket) {
                    Intent intent = new Intent(doctordesc.this, doctor_list_activity.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.tab_profile) {
                    Intent intent = new Intent(doctordesc.this, profile.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });
        }

}
