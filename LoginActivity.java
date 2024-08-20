package com.example.myapplication;

// Import necessary Android and third-party libraries
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity { // Define the LoginActivity class which extends AppCompatActivity
    private EditText usernameET, passwordET; // Declare EditText variables for username and password input fields
    private Button loginBtn; // Declare a Button variable for the login button
    private int attempts = 0; // Initialize a counter for tracking login attempts
    private SQLiteHelper dbHelper; // Declare a variable for the SQLiteHelper class to interact with the database

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Override the onCreate method which is called when the activity is created
        super.onCreate(savedInstanceState); // Call the superclass's onCreate method to initialize the activity
        setContentView(R.layout.activity_login); // Set the content view to the activity_login layout

        usernameET = findViewById(R.id.usernameET); // Find and link the EditText for the username input field
        passwordET = findViewById(R.id.passwordET); // Find and link the EditText for the password input field
        loginBtn = findViewById(R.id.loginBtn); // Find and link the Button for the login action

        dbHelper = new SQLiteHelper(this); // Initialize the SQLiteHelper to manage database operations

        loginBtn.setOnClickListener(new View.OnClickListener() { // Set an OnClickListener for the login button
            @Override
            public void onClick(View v) { // Override the onClick method to define the button's behavior when clicked
                String username = usernameET.getText().toString(); // Get the username input as a string
                String password = passwordET.getText().toString(); // Get the password input as a string

                if (dbHelper.checkUser(username, password)) { // Check if the username and password match a record in the database
                    Snackbar.make(v, "Login successful", Snackbar.LENGTH_SHORT).show(); // Show a Snackbar for successful login
                    Intent intent = new Intent(LoginActivity.this, SuccessfulLoginActivity.class); // Create an intent to start the SuccessfulLoginActivity
                    startActivity(intent); // Start the new activity
                } else { // If the username or password is incorrect
                    attempts++; // Increment the login attempts counter
                    if (attempts < 2) { // If attempts are less than 2
                        Snackbar.make(v, "Login failed", Snackbar.LENGTH_SHORT).show(); // Show a Snackbar for a failed login
                    } else { // If attempts are 2 or more
                        Snackbar.make(v, "Failed login attempts", Snackbar.LENGTH_LONG).show(); // Show a longer Snackbar for repeated failed attempts
                        loginBtn.setEnabled(false); // Disable the login button to prevent further attempts
                    }
                }
            }
        });
    }
}
