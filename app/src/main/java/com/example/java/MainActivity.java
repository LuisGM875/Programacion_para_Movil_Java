package com.example.java;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button selectFileButton;
    private Button uploadButton;
    private TextView filePathTextView;
    private Uri selectedFileUri;

    private final ActivityResultLauncher<Intent> filePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedFileUri = result.getData().getData();
                    if (selectedFileUri != null) {
                        String filePath = selectedFileUri.toString();
                        filePathTextView.setText(filePath);
                        Toast.makeText(this, "Archivo seleccionado: " + filePath, Toast.LENGTH_LONG).show();
                    }
                }
            });

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

        selectFileButton = findViewById(R.id.selectFileButton);
        uploadButton = findViewById(R.id.button);
        filePathTextView = findViewById(R.id.filePathTextView);

        selectFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        filePickerLauncher.launch(intent);
    }

    @SuppressLint("SetTextI18n")
    private void uploadFile() {
        if (selectedFileUri != null) {
            Toast.makeText(this, "Archivo subido: " + selectedFileUri.toString(), Toast.LENGTH_LONG).show();
            selectedFileUri = null;
            filePathTextView.setText("Ningún archivo seleccionado");
        } else {
            Toast.makeText(this, "Por favor, seleccione un archivo primero", Toast.LENGTH_SHORT).show();
        }
    }
}