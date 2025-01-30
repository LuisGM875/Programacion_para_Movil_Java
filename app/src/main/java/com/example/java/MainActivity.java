package com.example.java;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
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
        EditText cardNumberEditText = findViewById(R.id.editTextText2);
        EditText expirationDateEditText = findViewById(R.id.editTextText3);
        EditText cvvEditText = findViewById(R.id.editTextTextPassword);
        Button payButton = findViewById(R.id.button);

        // Set input filters
        cardNumberEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
        cvvEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});

        // Set a click listener for the pay button
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText fields
                String cardNumber = cardNumberEditText.getText().toString();
                String expirationDate = expirationDateEditText.getText().toString();
                String cvv = cvvEditText.getText().toString();

                // Validate the fields
                if (cardNumber.isEmpty() || expirationDate.isEmpty() || cvv.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidCardNumber(cardNumber)) {
                    Toast.makeText(MainActivity.this, "Please enter a valid 12-digit card number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidExpirationDate(expirationDate)) {
                    Toast.makeText(MainActivity.this, "Please enter a valid expiration date in MM/YY format", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidCvv(cvv)) {
                    Toast.makeText(MainActivity.this, "Please enter a valid 3-digit CVV", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Do something with the data (e.g., process payment, etc.)
                // For now, let's just show a Toast message
                Toast.makeText(MainActivity.this, "Card Number: " + cardNumber + ", Expiration Date: " + expirationDate + ", CVV: " + cvv, Toast.LENGTH_LONG).show();
            }
        });
    }

    // Card number validation method
    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber.length() == 12 && TextUtils.isDigitsOnly(cardNumber);
    }

    // Expiration date validation method (MM/YY format)
    private boolean isValidExpirationDate(String expirationDate) {
        if (expirationDate.length() != 5 || expirationDate.charAt(2) != '/') {
            return false;
        }

        String monthStr = expirationDate.substring(0, 2);
        String yearStr = expirationDate.substring(3, 5);

        try {
            int month = Integer.parseInt(monthStr);
            int year = Integer.parseInt(yearStr);

            if (month < 1 || month > 12) {
                return false;
            }
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    // CVV validation method
    private boolean isValidCvv(String cvv) {
        return cvv.length() == 3 && TextUtils.isDigitsOnly(cvv);
    }
}