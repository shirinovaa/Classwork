package com.example.classwork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.classwork.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpListener();
        setUp();
    }

    private void setUp() {
        Intent intent = getIntent();
        binding.imAvatar.setImageURI(intent.getData());

    }

    private void setUpListener() {
        binding.btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.btnSendData.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                intent.putExtra("key",name);

            }
        });


    }
}