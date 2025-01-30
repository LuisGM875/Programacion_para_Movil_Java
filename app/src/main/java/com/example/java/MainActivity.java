package com.example.java;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the views by their IDs
        EditText checkInDateEditText = findViewById(R.id.editTextText2);
        EditText checkOutDateEditText = findViewById(R.id.editTextText3);
        EditText numPeopleEditText = findViewById(R.id.editTextTextPassword);
        Button reserveButton = findViewById(R.id.button);

        // Set input filter for numPeopleEditText to allow only two digits
        numPeopleEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});

        // Set a click listener for the reserve button
        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText fields
                String checkInDate = checkInDateEditText.getText().toString();
                String checkOutDate = checkOutDateEditText.getText().toString();
                String numPeople = numPeopleEditText.getText().toString();

                // Validate the fields
                if (checkInDate.isEmpty() || checkOutDate.isEmpty() || numPeople.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidDate(checkInDate) || !isValidDate(checkOutDate)) {
                    Toast.makeText(MainActivity.this, "Please enter dates in DD/MM/YYYY format", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidNumPeople(numPeople)) {
                    Toast.makeText(MainActivity.this, "Number of people must be a number between 0 and 99", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Do something with the data (e.g., validate, save, etc.)
                // For now, let's just show a Toast message
                Toast.makeText(MainActivity.this, "Check-in: " + checkInDate + ", Check-out: " + checkOutDate + ", People: " + numPeople, Toast.LENGTH_LONG).show();
            }
        });
    }

    // Date validation method
    private boolean isValidDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Number of people validation method
    private boolean isValidNumPeople(String numPeopleStr) {
        try {
            int numPeople = Integer.parseInt(numPeopleStr);
            return numPeople >= 0 && numPeople <= 99;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}