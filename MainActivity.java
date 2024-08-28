package com.example.myapplication; // Define the package name for the application
// Import necessary Android and third-party libraries
import android.os.Bundle; // Import Bundle for saving instance state
import android.view.View; // Import View for handling UI components
import android.widget.Button; // Import Button class for UI buttons

import android.content.Intent; // Import Intent to start new activities

import androidx.appcompat.app.AppCompatActivity; // Import AppCompatActivity as a base class for activities

public class MainActivity extends AppCompatActivity { // Define MainActivity class, extending AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Override onCreate method, called when the activity is created
        super.onCreate(savedInstanceState); // Call superclass's onCreate method to initialize the activity
        setContentView(R.layout.activity_main); // Set the content view to the activity_main layout
        Button signUpBtn = findViewById(R.id.signUpBtn); // Find and link the sign-up button from the layout
        Button loginBtn = findViewById(R.id.loginBtn); // Find and link the login button from the layout

        signUpBtn.setOnClickListener(new View.OnClickListener() { // Set an OnClickListener for the sign-up button
            @Override
            public void onClick(View v) { // Override onClick method to define the button's behavior when clicked
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class); // Create an intent to start the SignUpActivity
                startActivity(intent); // Start the new SignUpActivity
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() { // Set an OnClickListener for the login button
            @Override
            public void onClick(View v) { // Override onClick method to define the button's behavior when clicked
                Intent intent = new Intent(MainActivity.this, LoginActivity.class); // Create an intent to start the LoginActivity
                startActivity(intent); // Start the new LoginActivity
            }
        });
    }
}
