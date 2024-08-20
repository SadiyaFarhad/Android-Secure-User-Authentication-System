package com.example.myapplication; // Define the package name for the application

// Import necessary Android libraries for database management and context handling
import android.content.ContentValues; // Import ContentValues to store key-value pairs for database insertion
import android.content.Context; // Import Context to provide access to application-specific resources
import android.database.Cursor; // Import Cursor to iterate over database query results
import android.database.sqlite.SQLiteDatabase; // Import SQLiteDatabase for database operations
import android.database.sqlite.SQLiteOpenHelper; // Import SQLiteOpenHelper for managing database creation and version management

public class SQLiteHelper extends SQLiteOpenHelper { // Define SQLiteHelper class, extending SQLiteOpenHelper for database management

    private static final String DATABASE_NAME = "login.db"; // Define the name of the database
    private static final int DATABASE_VERSION = 2; // Increment the version to 2 to apply schema changes

    public SQLiteHelper(Context context) { // Constructor for SQLiteHelper
        super(context, DATABASE_NAME, null, DATABASE_VERSION); // Call the superclass constructor with the database name and version
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // Override onCreate method, called when the database is created for the first time
        db.execSQL("CREATE TABLE users (" +
                "username TEXT PRIMARY KEY, " +
                "password TEXT, " +
                "gender TEXT)"); // Create table with the new gender column
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // Override onUpgrade method, called when the database needs to be upgraded
        if (oldVersion < 2) { // Check if the old version is less than 2
            db.execSQL("ALTER TABLE users ADD COLUMN gender TEXT"); // Add the gender column if upgrading from version 1
        }
    }

    public boolean insertUser(String username, String password, String gender) { // Method to insert a new user into the database
        SQLiteDatabase db = this.getWritableDatabase(); // Get writable access to the database
        ContentValues values = new ContentValues(); // Create a ContentValues object to store the values for the new user
        values.put("username", username); // Add the username to the ContentValues object
        values.put("password", password); // Add the password to the ContentValues object
        values.put("gender", gender); // Add gender to ContentValues
        long result = db.insert("users", null, values); // Insert the new user into the "users" table and get the result (row ID or -1 if failed)
        db.close(); // Close the database connection
        return result != -1; // Return true if the insert was successful, false otherwise
    }

    public boolean checkUser(String username, String password) { // Method to check if a user exists in the database with the provided username and password
        SQLiteDatabase db = this.getReadableDatabase(); // Get readable access to the database
        Cursor cursor = db.query("users", new String[]{"username", "password"}, "username = ? AND password = ?", new String[]{username, password}, null, null, null); // Query the "users" table to find a matching username and password
        boolean exists = cursor.moveToFirst(); // Check if the query returned a result (i.e., the user exists)
        cursor.close(); // Close the cursor to free up resources
        db.close(); // Close the database connection
        return exists; // Return true if the user exists, false otherwise
    }
}
