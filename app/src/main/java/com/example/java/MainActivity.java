package com.example.java;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
        EditText productNameEditText = findViewById(R.id.editTextText2);
        Spinner spinnerCategoria = findViewById(R.id.spinnerCategoria);
        Button searchButton = findViewById(R.id.button);

        // Spinner setup (same as before)
        String[] categorias = {
                "Seleccione una opción",
                "Electrónica",
                "Ropa",
                "Hogar"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categorias) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if (position == 0) {
                    view.setEnabled(false);
                }
                return view;
            }
        };
        spinnerCategoria.setAdapter(adapter);
        spinnerCategoria.setSelection(0);

        // Set a click listener for the search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText field
                String productName = productNameEditText.getText().toString();

                // Get the selected category from the spinner
                String selectedCategory = spinnerCategoria.getSelectedItem().toString();

                // Validate if a category is selected
                if (selectedCategory.equals("Seleccione una opción")) {
                    Toast.makeText(MainActivity.this, "Please select a category", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (productName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a product name", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Do something with the data (e.g., search, etc.)
                // For now, let's just show a Toast message
                Toast.makeText(MainActivity.this, "Product Name: " + productName + ", Category: " + selectedCategory, Toast.LENGTH_LONG).show();
            }
        });

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // No need to do anything here, the validation is done in the button's onClick
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No need to do anything here
            }
        });
    }
}