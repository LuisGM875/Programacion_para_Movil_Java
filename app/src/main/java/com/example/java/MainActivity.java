package com.example.java;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        EditText nameEditText = findViewById(R.id.editTextText2);
        EditText emailEditText = findViewById(R.id.editTextText3);
        RadioButton radioSi = findViewById(R.id.radio_si);
        RadioButton radioNo = findViewById(R.id.radio_no);
        Button registerButton = findViewById(R.id.button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();

                if (!isValidEmail(email)) {
                    Toast.makeText(MainActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                String attendance = "";
                if (radioSi.isChecked()) {
                    attendance = "Sí";
                } else if (radioNo.isChecked()) {
                    attendance = "No";
                } else {
                    attendance = "No seleccionado";
                }

                Toast.makeText(MainActivity.this, "Name: " + name + ", Email: " + email + ", Attendance: " + attendance, Toast.LENGTH_LONG).show();
            }
        });
        radioSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioNo.setChecked(false);
            }
        });

        radioNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioSi.setChecked(false);
            }
        });
    }

    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}