package com.example.hospital;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private List<CartItem> cartItemList;
    private DatabaseReference cartRef;
    private double totalPrice = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Setup OnBackPressedDispatcher for handling back button press
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle back press logic here
                finish(); // Close the activity and go back
            }
        });

        recyclerView = findViewById(R.id.cart_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartItemList = new ArrayList<>();

        // Firebase reference to the cart node
        cartRef = FirebaseDatabase.getInstance().getReference("cart");

        // Load cart items from Firebase
        loadCartItemsFromFirebase();

        adapter = new CartAdapter(this, cartItemList, new CartAdapter.OnQuantityChangeListener() {
            @Override
            public void onQuantityChanged(CartItem item) {
                updateTotalPrice();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    // Load cart items from Firebase
    private void loadCartItemsFromFirebase() {
        cartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartItemList.clear(); // Clear the list before adding new data
                totalPrice = 0.0; // Reset total price

                // Iterate through all items in the "cart" node
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CartItem cartItem = snapshot.getValue(CartItem.class);
                    if (cartItem != null) {
                        cartItemList.add(cartItem); // Add item to list
                    }
                }

                // Notify the adapter that data has changed
                adapter.notifyDataSetChanged();
                updateTotalPrice(); // Update total price
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CartActivity.this, "Failed to load cart items.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Update total price based on cart items
    private void updateTotalPrice() {
        totalPrice = 0.0;
        for (CartItem item : cartItemList) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        TextView totalPriceView = findViewById(R.id.total_price);
        totalPriceView.setText(String.format("Total: $%.2f", totalPrice));
    }

    // Handle back button press in action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back arrow in the action bar
            finish(); // Close the current activity and go back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
