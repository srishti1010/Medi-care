package com.example.hospital;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PaymentDetailActivity extends AppCompatActivity {

    private TextView paymentMethodInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize views
        paymentMethodInfo = findViewById(R.id.payment_method_info);

        // Get payment method from intent
        String paymentMethod = getIntent().getStringExtra("PAYMENT_METHOD");

        // Set content based on payment method
        if (paymentMethod != null) {
            paymentMethodInfo.setText("Enter your " + paymentMethod + " details below:");
            // Additional logic to add specific fields based on payment method
        }
    }

    @Override

    public boolean onSupportNavigateUp() {
        // Use the OnBackPressedDispatcher to handle the back navigation
        getOnBackPressedDispatcher().onBackPressed();
        return true;
    }

}
