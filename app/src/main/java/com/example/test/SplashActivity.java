package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

import java.io.FileOutputStream;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    static SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sp = getSharedPreferences("user info", MODE_PRIVATE);
        editor = sp.edit();
        editor.clear().apply();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }, this, 2000);
        }
    }

}