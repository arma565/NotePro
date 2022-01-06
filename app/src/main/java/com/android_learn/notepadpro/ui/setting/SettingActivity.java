package com.android_learn.notepadpro.ui.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.android_learn.notepadpro.config.AppConfig;
import com.android_learn.notepadpro.databinding.ActivitySettingBinding;
import com.android_learn.notepadpro.models.Language;
import com.android_learn.notepadpro.util.Utility;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;
    AppConfig appConfig;
    int position;
    String language = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        appConfig = new AppConfig(getApplicationContext());
        binding.imgBack.setOnClickListener(v -> finish());

        binding.spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.btnSave.setOnClickListener(v -> {

            if(position == Language.PERSIAN.getLanguage())
            {
                language = "fa";
            }else
            {
                language = "en";
            }
            appConfig.setLanguage(language);
            Utility.triggerRebirth(SettingActivity.this);
        });


    }
}