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

        EditText cardNumberEditText = findViewById(R.id.editTextText2);
        EditText expirationDateEditText = findViewById(R.id.editTextText3);
        EditText cvvEditText = findViewById(R.id.editTextTextPassword);
        Button payButton = findViewById(R.id.button);

        cardNumberEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
        cvvEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNumber = cardNumberEditText.getText().toString();
                String expirationDate = expirationDateEditText.getText().toString();
                String cvv = cvvEditText.getText().toString();

                if (cardNumber.isEmpty() || expirationDate.isEmpty() || cvv.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidCardNumber(cardNumber)) {
                    Toast.makeText(MainActivity.this, "Por favor ingresa un número de tarjeta válido de 12 dígitos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidExpirationDate(expirationDate)) {
                    Toast.makeText(MainActivity.this, "Por favor ingresa una fecha de expiración válida en el formato MM/YY", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidCvv(cvv)) {
                    Toast.makeText(MainActivity.this, "Por favor ingresa un CVV válido de 3 dígitos", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(MainActivity.this, "Número de Tarjeta: " + cardNumber + ", Fecha de Expiración: " + expirationDate + ", CVV: " + cvv, Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber.length() == 12 && TextUtils.isDigitsOnly(cardNumber);
    }

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

    private boolean isValidCvv(String cvv) {
        return cvv.length() == 3 && TextUtils.isDigitsOnly(cvv);
    }
}