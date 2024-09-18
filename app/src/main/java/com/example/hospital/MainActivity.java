package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Define the splash screen duration (3 seconds)
    private static final int SPLASH_SCREEN_DURATION = 3000; // 3000 ms = 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Delay for 3 seconds and then open the login screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start LoginActivity
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                // Finish MainActivity so that the user can't go back to it
                finish();
            }
        }, SPLASH_SCREEN_DURATION);
    }
}
