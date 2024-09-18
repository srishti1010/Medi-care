package com.example.hospital;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class MedicineActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MedicineAdapter adapter;
    private List<Medicine> medicineList;
    private DatabaseReference medicineRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Medicines");
        }

        recyclerView = findViewById(R.id.medicine_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        medicineList = new ArrayList<>();
        medicineRef = FirebaseDatabase.getInstance().getReference("medicines");

        // Add medicines to the list (populate once or check for duplicates)
        populateMedicineList();

        // Set adapter for RecyclerView
        adapter = new MedicineAdapter(this, medicineList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle back button click
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close the activity and return to the previous one
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Method to populate the medicine list (add to Firebase once, not every time the activity is opened)
    private void populateMedicineList() {
        medicineList.add(new Medicine("Aspirin", 12.99, 10.0, "https://example.com/aspirin.jpg"));
        medicineList.add(new Medicine("Ibuprofen", 9.99, 15.0, "https://example.com/ibuprofen.jpg"));
        medicineList.add(new Medicine("Paracetamol", 8.49, 5.0, "https://example.com/paracetamol.jpg"));
        medicineList.add(new Medicine("Amoxicillin", 18.49, 20.0, "https://example.com/amoxicillin.jpg"));
        medicineList.add(new Medicine("Cough Syrup", 7.99, 8.0, "https://example.com/cough_syrup.jpg"));
        medicineList.add(new Medicine("Vitamin C", 11.49, 12.0, "https://example.com/vitamin_c.jpg"));
        medicineList.add(new Medicine("Omeprazole", 14.99, 10.0, "https://example.com/omeprazole.jpg"));
        medicineList.add(new Medicine("Loratadine", 13.49, 6.0, "https://example.com/loratadine.jpg"));
        medicineList.add(new Medicine("Metformin", 16.99, 18.0, "https://example.com/metformin.jpg"));
        medicineList.add(new Medicine("Lipitor", 21.99, 25.0, "https://example.com/lipitor.jpg"));
        medicineList.add(new Medicine("Hydrochlorothiazide", 15.99, 14.0, "https://example.com/hydrochlorothiazide.jpg"));
        medicineList.add(new Medicine("Cetirizine", 10.49, 9.0, "https://example.com/cetirizine.jpg"));
        medicineList.add(new Medicine("Gabapentin", 19.49, 22.0, "https://example.com/gabapentin.jpg"));
        medicineList.add(new Medicine("Doxycycline", 17.99, 20.0, "https://example.com/doxycycline.jpg"));
        medicineList.add(new Medicine("Metoprolol", 14.49, 15.0, "https://example.com/metoprolol.jpg"));
        medicineList.add(new Medicine("Prednisone", 13.99, 12.0, "https://example.com/prednisone.jpg"));
        medicineList.add(new Medicine("Simvastatin", 20.49, 28.0, "https://example.com/simvastatin.jpg"));
        medicineList.add(new Medicine("Amlodipine", 11.99, 8.0, "https://example.com/amlodipine.jpg"));
        medicineList.add(new Medicine("Warfarin", 22.99, 30.0, "https://example.com/warfarin.jpg"));
        medicineList.add(new Medicine("Zyrtec", 12.49, 10.0, "https://example.com/zyrtec.jpg"));



         for (Medicine medicine : medicineList) {
             medicineRef.push().setValue(medicine);
         }
    }
}
