package com.example.hospital;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDetailActivity extends AppCompatActivity {

    private EditText editTextName, editTextFatherName, editTextMotherName, editTextAge,
            editTextBloodGroup, editTextPhone, editTextEmail, editTextMedicalHistory, editTextKids;
    private Button buttonSave;
    private LinearLayout linearLayout;
    private ImageView imageViewBack;

    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user); // Ensure this matches the XML layout file

        // Initialize the views
        linearLayout = findViewById(R.id.linearLayout);
        editTextName = findViewById(R.id.editTextName);
        editTextFatherName = findViewById(R.id.editTextFatherName);
        editTextMotherName = findViewById(R.id.editTextMotherName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextBloodGroup = findViewById(R.id.editTextBloodGroup);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMedicalHistory = findViewById(R.id.editTextMedicalHistory);
        editTextKids = findViewById(R.id.editTextKids);
        buttonSave = findViewById(R.id.buttonSave);
        imageViewBack = findViewById(R.id.imageViewBack); // Initialize back arrow

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Set up button click listener
        buttonSave.setOnClickListener(v -> saveUserDetails());

        // Set up back arrow click listener
        imageViewBack.setOnClickListener(v -> onBackPressed());
    }

    private void saveUserDetails() {
        String name = editTextName.getText().toString().trim();
        String fatherName = editTextFatherName.getText().toString().trim();
        String motherName = editTextMotherName.getText().toString().trim();
        String ageStr = editTextAge.getText().toString().trim();
        String bloodGroup = editTextBloodGroup.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String medicalHistory = editTextMedicalHistory.getText().toString().trim();
        String kids = editTextKids.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(fatherName) || TextUtils.isEmpty(motherName) ||
                TextUtils.isEmpty(ageStr) || TextUtils.isEmpty(bloodGroup) || TextUtils.isEmpty(phone) ||
                TextUtils.isEmpty(email) || TextUtils.isEmpty(medicalHistory) || TextUtils.isEmpty(kids)) {
            Toast.makeText(UserDetailActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            Toast.makeText(UserDetailActivity.this, "Invalid age", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a User object
        User user = new User(name, fatherName, motherName, age, bloodGroup, phone, email, medicalHistory, kids);

        // Generate a unique ID and save user details
        String userId = databaseReference.push().getKey();
        if (userId != null) {
            databaseReference.child(userId).setValue(user)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(UserDetailActivity.this, "Details saved successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UserDetailActivity.this, "Failed to save details", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    // User class to store user data
    public static class User {
        public String name, fatherName, motherName, bloodGroup, phone, email, medicalHistory, kids;
        public int age;

        public User() {
            // Default constructor required for Firebase
        }

        public User(String name, String fatherName, String motherName, int age, String bloodGroup,
                    String phone, String email, String medicalHistory, String kids) {
            this.name = name;
            this.fatherName = fatherName;
            this.motherName = motherName;
            this.age = age;
            this.bloodGroup = bloodGroup;
            this.phone = phone;
            this.email = email;
            this.medicalHistory = medicalHistory;
            this.kids = kids;
        }
    }
}
