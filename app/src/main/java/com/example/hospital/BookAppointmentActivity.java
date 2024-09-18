package com.example.hospital;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BookAppointmentActivity extends AppCompatActivity {

    private static final String TAG = "BookAppointmentActivity";

    private TextView dateLabel;
    private TextView timeLabel;
    private RecyclerView doctorRecyclerView;
    private Button btnBookAppointment;
    private EditText patientNameEditText;

    private BookAppointmentAdapter doctorAdapter;
    private SimpleDoctor selectedDoctor;
    private List<SimpleDoctor> doctorList;

    private DatabaseReference appointmentRef;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Initialize Firebase Database reference
        appointmentRef = FirebaseDatabase.getInstance().getReference("appointments");

        // Initialize views
        dateLabel = findViewById(R.id.dateLabel);
        timeLabel = findViewById(R.id.timeLabel);
        doctorRecyclerView = findViewById(R.id.doctorRecyclerView);
        btnBookAppointment = findViewById(R.id.btnBookAppointment);
        patientNameEditText = findViewById(R.id.patientNameEditText);

        // Setup Action Bar with back button (Up button)
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

        // Initialize doctor list and adapter
        doctorList = getDoctors();
        doctorAdapter = new BookAppointmentAdapter(doctorList, this, patientNameEditText.getText().toString(), doctor -> selectedDoctor = doctor);
        doctorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorRecyclerView.setAdapter(doctorAdapter);

        // Set up Date Picker
        dateLabel.setOnClickListener(v -> new DatePickerDialog(
                BookAppointmentActivity.this,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    updateDateLabel();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show());

        // Set up Time Picker
        timeLabel.setOnClickListener(v -> new TimePickerDialog(
                BookAppointmentActivity.this,
                (view, hourOfDay, minute) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);
                    updateTimeLabel();
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        ).show());

        // Handle Book Appointment Button click
        btnBookAppointment.setOnClickListener(v -> {
            Log.d(TAG, "Book Appointment button clicked");
            bookAppointment();
        });
    }

    // Handle ActionBar (Up) back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close the activity and go back when the Up button is pressed
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<SimpleDoctor> getDoctors() {
        // Example static data
        return new ArrayList<>(List.of(
                new SimpleDoctor("Dr. John Smith", "Cardiologist"),
                new SimpleDoctor("Dr. Emily Davis", "Neurologist"),
                new SimpleDoctor("Dr. Michael Brown", "Orthopedic Surgeon")
        ));
    }

    private void updateDateLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateLabel.setText("Date: " + sdf.format(calendar.getTime()));
    }

    private void updateTimeLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        timeLabel.setText("Time: " + sdf.format(calendar.getTime()));
    }

    private void bookAppointment() {
        if (selectedDoctor == null) {
            Log.w(TAG, "No doctor selected");
            return;
        }

        String selectedDate = dateLabel.getText().toString().replace("Date: ", "").trim();
        String selectedTime = timeLabel.getText().toString().replace("Time: ", "").trim();
        String patientName = patientNameEditText.getText().toString(); // Get the patient's name

        // Check if the fields are not empty
        if (selectedDate.isEmpty() || selectedTime.isEmpty() || patientName.isEmpty()) {
            Log.w(TAG, "Appointment details are incomplete");
            return;
        }

        // Create an appointment object with a unique ID
        String appointmentId = appointmentRef.push().getKey(); // Generate a unique ID
        Appointment appointment = new Appointment(
                appointmentId, // Set the generated ID
                selectedDate,
                selectedDoctor.getName(),
                patientName,
                selectedDoctor.getSpecialization(),
                selectedTime
        );

        // Log appointment details
        Log.d(TAG, "Booking appointment: " + appointment);

        // Save appointment to Firebase
        appointmentRef.child(appointmentId).setValue(appointment)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Appointment booked successfully"))
                .addOnFailureListener(e -> Log.e(TAG, "Failed to book appointment", e));
    }
}
