package com.example.finalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        progressBar=findViewById(R.id.progressbar1);
        progressBar.setVisibility(View.GONE);
    }

    public void login(View view) {
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        progressBar.setVisibility(View.VISIBLE);
    }

    public void registration(View view) {
        startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
        progressBar.setVisibility(View.VISIBLE);
    }
}