package com.android_learn.notepadpro.ui.addnote;

import android.content.Context;

import com.android_learn.notepadpro.models.Note;

public class NotePresenter implements IView {

    NoteInteract noteInteract;
    IView iView;
    public NotePresenter(Context context , IView iView)
    {
        noteInteract = new NoteInteract(context);
        this.iView = iView;
    }

    public void addNote(Note note)
    {
        noteInteract.addNote(note , this);
    }

    public void updateNote(Note note)
    {
        noteInteract.updateNote(note , this);
    }


    @Override
    public void onSuccess() {
        iView.onSuccess();
    }

    @Override
    public void onFailure() {
    iView.onFailure();
    }

    @Override
    public void EmptyTitle() {
    iView.EmptyTitle();
    }

    @Override
    public void EmptyDescription() {
    iView.EmptyDescription();
    }

    @Override
    public void EmptyTime() {
    iView.EmptyTime();
    }

    @Override
    public void EmptyDate() {
    iView.EmptyDate();
    }
}
