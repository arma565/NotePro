package com.android_learn.notepadpro.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.android_learn.notepadpro.models.Note;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapterControl extends NoteDatabase{
    public DatabaseAdapterControl(@Nullable Context context) {
        super(context);
    }

    public long insertNote(Note note)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE , note.getTitle());
        cv.put(COLUMN_DESCRIPTION , note.getDescription());
        cv.put(COLUMN_TIME_NOTE , note.getTimeNote());
        cv.put(COLUMN_DATE_NOTE , note.getDateNote());
        return db.insert(TABLE_NAME , null , cv);
    }

    public List<Note> readAllNotes()
    {
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " ORDER BY "+COLUMN_ID+" Desc ",null);
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
            String timeNote = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_NOTE));
            String dateNote = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE_NOTE));
            Note note = new Note(id,title,description,timeNote,dateNote);
            noteList.add(note);
        }
        return noteList;
    }

    public void deleteAll()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
    }

    public void deleteNote(int noteID)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=" + noteID, null);
    }

    public long updateNote(Note note)
    {

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE,note.getTitle());
        cv.put(COLUMN_DESCRIPTION,note.getDescription());
        cv.put(COLUMN_TIME_NOTE , note.getTimeNote());
        cv.put(COLUMN_DATE_NOTE , note.getDateNote());
        SQLiteDatabase db = getWritableDatabase();
        return db.update(TABLE_NAME,cv,"id="+note.getId(),null);

    }

    public List<Note> search(String titleSearch)
    {
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("Select * From "+TABLE_NAME+" WHERE title LIKE '%"+titleSearch+"%' " , null );
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
            String timeNote = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_NOTE));
            String dateNote = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE_NOTE));
            Note note = new Note(id,title,description,timeNote,dateNote);
            noteList.add(note);
        }
        return noteList;
    }

}
