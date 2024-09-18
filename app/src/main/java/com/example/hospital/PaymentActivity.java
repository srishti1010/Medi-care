package com.example.hospital;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PaymentActivity extends AppCompatActivity {

    private ListView paymentMethodsList;
    private String[] paymentMethods = {"Credit/Debit Card", "PayPal", "Bank Transfer"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

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

        // Initialize ListView for payment methods
        paymentMethodsList = findViewById(R.id.payment_methods_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, paymentMethods);
        paymentMethodsList.setAdapter(adapter);

        // Handle payment method selection
        paymentMethodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                String selectedMethod = paymentMethods[position];
                showPaymentDetailsDialog(selectedMethod);
            }
        });
    }

    private void showPaymentDetailsDialog(String method) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment Details");

        switch (method) {
            case "Credit/Debit Card":
                View cardView = getLayoutInflater().inflate(R.layout.dailog_credit_card, null);
                final EditText cardNumber = cardView.findViewById(R.id.cardNumber);
                final EditText cardExpiry = cardView.findViewById(R.id.cardExpiry);
                final EditText cardCVV = cardView.findViewById(R.id.cardCVV);

                builder.setView(cardView);
                builder.setMessage("Please enter your card details.");
                builder.setPositiveButton("OK", (dialog, which) -> {
                    String number = cardNumber.getText().toString();
                    String expiry = cardExpiry.getText().toString();
                    String cvv = cardCVV.getText().toString();

                    if (validateCardDetails(number, expiry, cvv)) {
                        Toast.makeText(PaymentActivity.this, "Processing Card Payment", Toast.LENGTH_SHORT).show();
                        // Proceed with payment processing
                    } else {
                        Toast.makeText(PaymentActivity.this, "Invalid card details", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case "PayPal":
                builder.setMessage("You will be redirected to PayPal.");
                builder.setPositiveButton("OK", (dialog, which) -> {
                    Toast.makeText(PaymentActivity.this, "Redirecting to PayPal", Toast.LENGTH_SHORT).show();
                    // Redirect to PayPal
                    Intent intent = new Intent(PaymentActivity.this, PayPalActivity.class);
                    startActivity(intent);
                });
                break;

            case "Bank Transfer":
                builder.setMessage("Please transfer the amount to the following bank account:\n" +
                        "Bank Name: XYZ Bank\n" +
                        "Account Number: 123456789\n" +
                        "IFSC Code: XYZ123\n" +
                        "Branch: Main Branch");
                builder.setPositiveButton("OK", (dialog, which) -> {
                    Toast.makeText(PaymentActivity.this, "Payment instructions noted", Toast.LENGTH_SHORT).show();
                    // Possibly handle bank transfer verification
                });
                break;
        }

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private boolean validateCardDetails(String number, String expiry, String cvv) {
        // Simple validation logic
        return !TextUtils.isEmpty(number) && !TextUtils.isEmpty(expiry) && !TextUtils.isEmpty(cvv)
                && number.length() >= 16 && expiry.length() >= 5 && cvv.length() >= 3;
    }

    @Override
    public boolean onSupportNavigateUp() {
        getOnBackPressedDispatcher().onBackPressed();
        return true;
    }
}
