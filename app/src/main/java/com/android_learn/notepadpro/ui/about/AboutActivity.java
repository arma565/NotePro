package com.android_learn.notepadpro.ui.about;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android_learn.notepadpro.databinding.ActivityAboutBinding;
import de.hdodenhof.circleimageview.BuildConfig;

public class AboutActivity extends AppCompatActivity {
    ActivityAboutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgBack.setOnClickListener(v -> finish());
        binding.txtVersion.setText(BuildConfig.VERSION_NAME);
    }
}