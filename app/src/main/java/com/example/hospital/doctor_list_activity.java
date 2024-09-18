package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class doctor_list_activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DoctorAdapter doctorAdapter;
    private BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor);

        recyclerView = findViewById(R.id.recycler_view_doctors);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Pass 'this' as context to DoctorAdapter
        doctorAdapter = new DoctorAdapter(doctor_list.getDoctors(), this);
        recyclerView.setAdapter(doctorAdapter);

        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.tab_home) {
                startActivity(new Intent(doctor_list_activity.this, home.class));
                return true;
            } else if (itemId == R.id.tab_categories) {
                startActivity(new Intent(doctor_list_activity.this, categories.class));
                return true;
            } else if (itemId == R.id.tab_basket) {
                startActivity(new Intent(doctor_list_activity.this, doctor_list_activity.class));
                return true;
            } else if (itemId == R.id.tab_profile) {
                Intent intent = new Intent(doctor_list_activity.this, profile.class);
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
    }
}
