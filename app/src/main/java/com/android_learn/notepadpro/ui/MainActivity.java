package com.android_learn.notepadpro.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android_learn.notepadpro.R;
import com.android_learn.notepadpro.ui.about.AboutActivity;
import com.android_learn.notepadpro.ui.addnote.AddNoteActivity;
import com.android_learn.notepadpro.ui.contact.ContactActivity;
import com.android_learn.notepadpro.ui.search.SearchActivity;
import com.android_learn.notepadpro.ui.setting.SettingActivity;
import com.android_learn.notepadpro.adapter.NoteAdapter;
import com.android_learn.notepadpro.config.AppConfig;
import com.android_learn.notepadpro.database.DatabaseAdapterControl;
import com.android_learn.notepadpro.databinding.ActivityMainBinding;
import com.android_learn.notepadpro.models.Type;
import com.android_learn.notepadpro.util.Utility;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DatabaseAdapterControl databaseAdapterControl;
    AppConfig config;
    NoteAdapter noteAdapter;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /////////////////////////////////////////////////////////////////
        config = new AppConfig(MainActivity.this);
        Locale locale = new Locale(config.getLanguage());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        /////////////////////////////////////////////////////////////////
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /////////////////////////////////////////////////////////////////
        databaseAdapterControl = new DatabaseAdapterControl(getApplicationContext());
        noteAdapter = new NoteAdapter(getApplicationContext(), databaseAdapterControl.readAllNotes());
        /////////////////////////////////////////////////////////////////
        setSupportActionBar(binding.toolbarMain);
        /////////////////////////////////////////////////////////////////
        binding.floatBtn.setOnClickListener(v -> {
            Intent intent_add_note = new Intent(getApplicationContext(), AddNoteActivity.class);
            intent_add_note.putExtra("type", Type.ADD.getType());
            startActivity(intent_add_note);
        });
        /////////////////////////////////////////////////////////////////

        binding.navi.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_add:
                    Intent intent_add_note = new Intent(getApplicationContext(), AddNoteActivity.class);
                    intent_add_note.putExtra("type", Type.ADD.getType());
                    startActivity(intent_add_note);
                    break;
                case R.id.item_deleteAll:
                    databaseAdapterControl.deleteAll();
                    Utility.triggerRebirth(MainActivity.this);
                    break;
                case R.id.item_about:
                    Intent intent_about = new Intent(getApplicationContext(), AboutActivity.class);
                    startActivity(intent_about);
                    break;
                case R.id.item_contact:
                    Intent intent_contact = new Intent(getApplicationContext(), ContactActivity.class);
                    startActivity(intent_contact);
                    break;
                case R.id.item_setting:
                    Intent intent_setting = new Intent(getApplicationContext(), SettingActivity.class);
                    startActivity(intent_setting);
                    break;
            }
            return false;
        });
        /////////////////////////////////////////////////////////////////
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, binding.drawerLayout, binding.toolbarMain
                , R.string.open, R.string.close);
        toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_addNewNote:
                Intent intent_add_note = new Intent(getApplicationContext(), AddNoteActivity.class);
                intent_add_note.putExtra("type", Type.ADD.getType());
                startActivity(intent_add_note);
                break;
            case R.id.item_about:
                Intent intent_about = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent_about);
                break;
            case R.id.item_contact:
                Intent intent_contact = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(intent_contact);
                break;
            case R.id.item_setting:
                Intent intent_setting = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent_setting);
                break;
            case R.id.item_search:
                Intent intent_search = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent_search);
                break;
            case R.id.item_exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        noteAdapter = new NoteAdapter(getApplicationContext(), databaseAdapterControl.readAllNotes());
        binding.recyclerMain.setAdapter(noteAdapter);
        binding.recyclerMain.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle(R.string.exit);
        alert.setMessage(R.string.exit_message);
        alert.setIcon(android.R.drawable.ic_delete);
        alert.setPositiveButton(R.string.yes, (dialogInterface, i) -> finish());

        alert.setNegativeButton(R.string.no, (dialogInterface, i) -> {

        });

        alert.setNeutralButton(R.string.rate, (dialogInterface, i) -> Toast.makeText(getApplicationContext(), R.string.rate_app, Toast.LENGTH_LONG).show());
        alert.show();
    }
}