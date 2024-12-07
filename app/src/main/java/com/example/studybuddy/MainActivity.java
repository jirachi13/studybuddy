package com.example.studybuddy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigate to SplashScreenActivity
        Intent intent = new Intent(this, SplashScreenActivity.class);
        startActivity(intent);
        finish();
    }
}
