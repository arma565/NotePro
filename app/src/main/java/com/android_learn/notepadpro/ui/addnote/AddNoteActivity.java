package com.android_learn.notepadpro.ui.addnote;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android_learn.notepadpro.R;
import com.android_learn.notepadpro.databinding.ActivityAddNoteBinding;
import com.android_learn.notepadpro.models.Note;
import com.android_learn.notepadpro.models.Type;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Objects;

public class AddNoteActivity extends AppCompatActivity implements IView {
    ActivityAddNoteBinding binding;
    Bundle bundle;
    int type = 1;
    Note note;
    Calendar calendar = Calendar.getInstance();
    int mYear;
    int mMonth;
    int mDay;
    int mHour;
    int mMin;
    String time = "";
    String date = "";
    NotePresenter notePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notePresenter = new NotePresenter(this, this);
        bundle = getIntent().getExtras();
        type = bundle.getInt("type");
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMin = calendar.get(Calendar.MINUTE);
        binding.imgBack.setOnClickListener(v -> finish());

        if (type == Type.ADD.getType()) {
            Toast.makeText(getApplicationContext(), R.string.you_can_add_note, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.edit_note_here, Toast.LENGTH_LONG).show();
            binding.txtNote.setText(R.string.editNote);
            binding.btnAddNote.setText(R.string.editNote);
            note = bundle.getParcelable("data");
            binding.btnTime.setText(note.getTimeNote());
            binding.btnDate.setText(note.getDateNote());
            binding.title.setText(note.getTitle());
            binding.description.setText(note.getDescription());
        }
        //DatePicker
        binding.btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNoteActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date = year + "/" + month + "/" + dayOfMonth;
                        Log.e("","");
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        //TimePicker
        binding.btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddNoteActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time = hourOfDay + ":" + minute;
                        Log.e("","");
                    }
                }, mHour, mMin, false);
                timePickerDialog.show();
            }
        });

        binding.btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == Type.ADD.getType()) {
                    Note note = new Note();
                    note.setTitle(Objects.requireNonNull(binding.title.getText()).toString());
                    note.setDescription(Objects.requireNonNull(binding.description.getText()).toString());
                    note.setTimeNote(time);
                    note.setDateNote(date);
                    notePresenter.addNote(note);
                } else {
                    Note note_edit = new Note();
                    note_edit.setTitle(Objects.requireNonNull(binding.title.getText()).toString());
                    note_edit.setDescription(Objects.requireNonNull(binding.description.getText()).toString());
                    note_edit.setTimeNote(time);
                    note_edit.setDateNote(date);
                    note_edit.setId(note.getId());
                    notePresenter.updateNote(note_edit);
                }
            }
        });
    }
    @Override
    public void onSuccess() {
        Snackbar.make(binding.getRoot(),"Successful" , Snackbar.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onFailure() {
        Snackbar.make(binding.getRoot(),"unSuccessful" , Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void EmptyTitle() {
        binding.title.setError("Title Require");
    }

    @Override
    public void EmptyDescription() {
        binding.description.setError("Description Require");
    }

    @Override
    public void EmptyTime() {
       Toast.makeText(getApplicationContext() , "Time Require" , Toast.LENGTH_LONG).show();
    }

    @Override
    public void EmptyDate() {
        Toast.makeText(getApplicationContext() , "Date Require" , Toast.LENGTH_LONG).show();
    }
}


