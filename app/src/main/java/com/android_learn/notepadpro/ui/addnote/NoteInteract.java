package com.android_learn.notepadpro.ui.addnote;

import android.content.Context;
import android.util.Log;

import com.android_learn.notepadpro.database.DatabaseAdapterControl;
import com.android_learn.notepadpro.models.Note;

public class NoteInteract {

    DatabaseAdapterControl databaseAdapterControl;
    NoteInteract(Context context)
    {
        databaseAdapterControl = new DatabaseAdapterControl(context);
    }

    public void addNote(Note note , IView iView)
    {
        if(note.getTitle().isEmpty())
        {
            iView.EmptyTitle();
            return;
        }
        if(note.getDescription().isEmpty())
        {
            iView.EmptyDescription();
            return;
        }
        if(note.getTimeNote().isEmpty())
        {
            iView.EmptyTime();
            Log.e("","");
            return;
        }
        if(note.getDateNote().isEmpty())
        {
            iView.EmptyDate();
            Log.e("","");
            return;
        }
       long res =  databaseAdapterControl.insertNote(note);
        if(res > 0)
        {
            iView.onSuccess();
        }else
        {
            iView.onFailure();
        }
    }

    public void updateNote(Note note , IView iView)
    {
        if(note.getTitle().isEmpty())
        {
            iView.EmptyTitle();
            return;
        }
        if(note.getDescription().isEmpty())
        {
            iView.EmptyDescription();
            return;
        }
        if(note.getTimeNote().isEmpty())
        {
            iView.EmptyTime();
            return;
        }
        if(note.getDateNote().isEmpty())
        {
            iView.EmptyDate();
            return;
        }


        long res = databaseAdapterControl.updateNote(note);
        if(res > 0)
        {
            iView.onSuccess();

        }else
        {
            iView.onFailure();
        }

    }



}
