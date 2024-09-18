package com.example.hospital;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppointmentViewActivity extends AppCompatActivity {

    private static final String TAG = "AppointmentViewActivity";
    private DatabaseReference databaseReference;
    private TextView textViewDate, textViewDoctorName, textViewPatientName, textViewSpecialization, textViewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_view);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("appointments");

        // Initialize views
        textViewDate = findViewById(R.id.textViewDate);
        textViewDoctorName = findViewById(R.id.textViewDoctorName);
        textViewPatientName = findViewById(R.id.textViewPatientName);
        textViewSpecialization = findViewById(R.id.textViewSpecialization);
        textViewTime = findViewById(R.id.textViewTime);

        // Get appointment ID from intent
        String appointmentId = getIntent().getStringExtra("APPOINTMENT_ID");

        if (appointmentId != null) {
            Log.d(TAG, "Fetching appointment details for ID: " + appointmentId);
            fetchAppointmentDetails(appointmentId);
        } else {
            Log.e(TAG, "No appointment ID provided");
        }
    }

    private void fetchAppointmentDetails(String appointmentId) {
        databaseReference.child(appointmentId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Appointment appointment = dataSnapshot.getValue(Appointment.class);
                    if (appointment != null) {
                        displayData(appointment);
                    } else {
                        Log.e(TAG, "Failed to parse appointment data");
                    }
                } else {
                    Log.e(TAG, "No data found for appointment ID: " + appointmentId);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "DatabaseError: " + databaseError.getMessage());
            }
        });
    }

    private void displayData(Appointment appointment) {
        if (appointment != null) {
            textViewDate.setText("Date: " + appointment.getDate());
            textViewDoctorName.setText("Doctor: " + appointment.getDoctorName());
            textViewPatientName.setText("Patient: " + appointment.getPatientName());
            textViewSpecialization.setText("Specialization: " + appointment.getSpecialization());
            textViewTime.setText("Time: " + appointment.getTime());
        } else {
            Log.e(TAG, "Appointment data is null");
        }
    }
}
