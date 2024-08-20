package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class SignUpActivity extends AppCompatActivity {

    private EditText usernameET, passwordET, confirmPasswordET;
    private SQLiteHelper dbHelper;
    private Spinner genderSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        confirmPasswordET = findViewById(R.id.confirmPasswordET);
        genderSpinner = findViewById(R.id.genderSpinner);

        dbHelper = new SQLiteHelper(this);

        // Set up the Spinner
        String[] genderOptions = {"Select Gender", "Male", "Female", "Other", "Prefer not to say"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // This is the hint item, so handle it as necessary
                } else {
                    // Handle the actual selected item
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected
            }
        });

        Button signUpBtn = findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignUp(v);
            }
        });
    }

    private void handleSignUp(View view) {
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        String confirmPassword = confirmPasswordET.getText().toString();
        String gender = genderSpinner.getSelectedItem().toString();

        if (password.equals(confirmPassword)) {
            if (isPasswordValid(password)) {
                if (dbHelper.insertUser(username, password, gender)) {
                    Snackbar.make(view, "Sign up successful", Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Snackbar.make(view, "Sign up failed", Snackbar.LENGTH_LONG).show();
                }
            } else {
                Snackbar.make(view, "Password must contain at least one uppercase letter, one special character, and be at least 8 characters long", Snackbar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(view, "Passwords do not match", Snackbar.LENGTH_LONG).show();
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[!@#$%^&*()].*");
    }
}
