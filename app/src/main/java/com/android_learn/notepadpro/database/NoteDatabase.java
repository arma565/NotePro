package com.android_learn.notepadpro.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDatabase extends SQLiteOpenHelper {
    //DATABASE INSTRUCTION
    public static final String DB_NAME = "Note";
    public static final String TABLE_NAME = "tbl_note";
    public static final int VERSION = 1;
    //TABLE VALUES
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TIME_NOTE = "timeNote";
    public static final String COLUMN_DATE_NOTE = "dateNote";

    public static final String DB_Note = "create table "+TABLE_NAME+"("+COLUMN_ID+" integer primary key autoincrement , "+COLUMN_TITLE+" varchar(50) , "+COLUMN_DESCRIPTION+" varchar(255) , "+COLUMN_TIME_NOTE+" varchar(50) , "+COLUMN_DATE_NOTE+" varchar(50))";
    public NoteDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_Note);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
