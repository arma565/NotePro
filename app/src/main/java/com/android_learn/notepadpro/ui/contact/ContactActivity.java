package com.android_learn.notepadpro.ui.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.android_learn.notepadpro.databinding.ActivityContactBinding;

public class ContactActivity extends AppCompatActivity {
    ActivityContactBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgBack.setOnClickListener(v -> finish());

        binding.imgInstagram.setOnClickListener(v -> {
            Intent intent_instagram = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.instagram.com/arma_258/"));
            startActivity(intent_instagram);
        });
        binding.imgYahoo.setOnClickListener(v -> {
            Intent intent_yahoo = new Intent(Intent.ACTION_VIEW , Uri.parse("mailto:rezagorji68@yahoo.com"));
            startActivity(intent_yahoo);
        });
        binding.imgGmail.setOnClickListener(v -> {
            Intent intent_gmail = new Intent(Intent.ACTION_VIEW , Uri.parse("mailto:rezagorji68@gmail.com"));
            startActivity(intent_gmail);
        });
        binding.imgFacebook.setOnClickListener(v -> {
            Intent intent_facebook = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.facebook.com/Armareza/"));
            startActivity(intent_facebook);

        });
    }
}