package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalDetailActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child("userId"); // Replace "userId" with the actual user ID

        // Initialize views
        CircleImageView profileImage = findViewById(R.id.profile_image);
        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewFatherName = findViewById(R.id.textViewFatherName);
        TextView textViewMotherName = findViewById(R.id.textViewMotherName);
        TextView textViewAge = findViewById(R.id.textViewAge);
        TextView textViewEmail = findViewById(R.id.textViewEmail);
        TextView textViewPhone = findViewById(R.id.textViewPhone);
        TextView textViewBloodGroup = findViewById(R.id.textViewBloodGroup);
        TextView textViewKids = findViewById(R.id.textViewKids);
        TextView textViewDiseaseHistory = findViewById(R.id.textViewDiseaseHistory);
        ImageView imageViewBack = findViewById(R.id.imageViewBack);

        // Set up back button click listener
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity and return to the previous one
            }
        });

        // Fetch data
        fetchData();
    }

    private void fetchData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get user data
                User user = dataSnapshot.getValue(User.class);

                // Display user data
                if (user != null) {
                    displayData(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
                Log.e("FirebaseError", databaseError.getMessage());
            }
        });
    }

    private void displayData(User user) {
        CircleImageView profileImage = findViewById(R.id.profile_image);
        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewFatherName = findViewById(R.id.textViewFatherName);
        TextView textViewMotherName = findViewById(R.id.textViewMotherName);
        TextView textViewAge = findViewById(R.id.textViewAge);
        TextView textViewEmail = findViewById(R.id.textViewEmail);
        TextView textViewPhone = findViewById(R.id.textViewPhone);
        TextView textViewBloodGroup = findViewById(R.id.textViewBloodGroup);
        TextView textViewKids = findViewById(R.id.textViewKids);
        TextView textViewDiseaseHistory = findViewById(R.id.textViewDiseaseHistory);

        // Set data to views
        textViewName.setText("Name: " + user.getName()); // Assuming you have getter methods in User class
        textViewFatherName.setText("Father's Name: " + user.getFatherName());
        textViewMotherName.setText("Mother's Name: " + user.getMotherName());
        textViewAge.setText("Age: " + user.getAge());
        textViewEmail.setText("Email: " + user.getEmail());
        textViewPhone.setText("Phone Number: " + user.getPhone());
        textViewBloodGroup.setText("Blood Group: " + user.getBloodGroup());
        textViewKids.setText("Kids (if any): " + user.getKids());

        // Set disease history
        if (user.getDiseaseHistory() != null) {
            textViewDiseaseHistory.setText("Disease History: " + user.getDiseaseHistory());
        } else {
            textViewDiseaseHistory.setText("Disease History: No");
        }

        // You might need to load image from a URL if you have an image URL stored in Firebase
        // Glide.with(this).load(user.getProfileImageUrl()).into(profileImage);
    }
}
