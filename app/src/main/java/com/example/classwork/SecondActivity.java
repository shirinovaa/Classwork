package com.example.classwork;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.classwork.databinding.ActivityMainBinding;
import com.example.classwork.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;
    private final int PICK_IMAGE = 2;
    Uri uri;

    ActivityResultLauncher<String> result = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    uri= result;
                    binding.imAvatar.setImageURI(uri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupData();
        transition();
    }

    private void setupData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("key");
    }

    private void transition() {
        binding.btnBrowser.setOnClickListener(view -> {
            Intent browserIntent = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://yandex.ru"));
            startActivity(browserIntent);
        });

        binding.btnCamera.setOnClickListener(view -> {
            Intent intent = new
                    Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, 0);
        });

        binding.btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.launch("image/*");
            }
        });

        binding.btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone=557533788"));
                sendIntent.setPackage("com.whatsapp");
                SecondActivity.this.startActivity(sendIntent);
            }
        });

        binding.btnTransactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SecondActivity.this, MainActivity.class);
                i.setData(uri);
                startActivity(i);

            }
        });
    }
}