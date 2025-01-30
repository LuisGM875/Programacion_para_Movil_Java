package com.example.java;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        // Find the views by their IDs
        EditText nameEditText = findViewById(R.id.editTextText2);
        EditText emailEditText = findViewById(R.id.editTextText3);
        EditText commentEditText = findViewById(R.id.editTextTextMultiLine2);
        Button sendButton = findViewById(R.id.button);

        // Set a click listener for the send button
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText fields
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String comment = commentEditText.getText().toString();

                // Validate the fields
                if (name.isEmpty() || email.isEmpty() || comment.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidEmail(email)) {
                    Toast.makeText(MainActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Do something with the data (e.g., save, send, etc.)
                // For now, let's just show a Toast message
                Toast.makeText(MainActivity.this, "Name: " + name + ", Email: " + email + ", Comment: " + comment, Toast.LENGTH_LONG).show();
            }
        });
    }

    // Email validation method
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}