package com.android_learn.notepadpro.ui.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.android_learn.notepadpro.adapter.NoteAdapter;
import com.android_learn.notepadpro.database.DatabaseAdapterControl;
import com.android_learn.notepadpro.databinding.ActivitySearchBinding;
import com.android_learn.notepadpro.models.Note;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding binding;
    DatabaseAdapterControl databaseAdapterControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseAdapterControl = new DatabaseAdapterControl(getApplicationContext());
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Note> noteList_new = new ArrayList<>();
                noteList_new = databaseAdapterControl.search(s.toString());
                NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext() , noteList_new);
                binding.recSearch.setAdapter(noteAdapter);
                binding.recSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext() , RecyclerView.VERTICAL , false));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Note> noteList = databaseAdapterControl.readAllNotes();
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext() , noteList);
        binding.recSearch.setAdapter(noteAdapter);
        binding.recSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext() , LinearLayoutManager.VERTICAL , false));
    }
}