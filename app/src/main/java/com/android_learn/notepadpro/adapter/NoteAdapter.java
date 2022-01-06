package com.android_learn.notepadpro.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.android_learn.notepadpro.R;
import com.android_learn.notepadpro.ui.addnote.AddNoteActivity;
import com.android_learn.notepadpro.database.DatabaseAdapterControl;
import com.android_learn.notepadpro.models.Note;
import com.android_learn.notepadpro.models.Type;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteVH>{

    private List<Note> noteList;
    Context context;
    DatabaseAdapterControl adapterControl;
    public NoteAdapter(Context context, List<Note> list) {
        this.context = context;
        this.noteList = list;
        adapterControl = new DatabaseAdapterControl(context);
    }

    @NonNull
    @Override
    public NoteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.note_row, null);
        return new NoteVH(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull NoteVH holder, @SuppressLint("RecyclerView") int position) {
        Note note = noteList.get(position);
        holder.txt_title.setText(note.getTitle());
        holder.txt_description.setText(note.getDescription());
        holder.txt_time.setText(note.getTimeNote());
        holder.txt_date.setText(note.getDateNote());
        //img delete
        holder.img_delete.setOnClickListener(v -> {
            notifyItemRemoved(position);
            noteList.remove(position);
            notifyItemRangeRemoved(position, noteList.size());
            adapterControl.deleteNote(note.getId());
            notifyDataSetChanged();
        });
        //img share
        holder.img_share.setOnClickListener(v -> {
            Intent txtIntent = new Intent(Intent.ACTION_SEND);
            txtIntent.setType("text/plain");
            txtIntent.putExtra(Intent.EXTRA_SUBJECT, note.getTitle());
            txtIntent.putExtra(Intent.EXTRA_TEXT, note.getDescription());
            txtIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(Intent.createChooser(txtIntent, "Share"));
        });
        //img edit
        holder.img_edit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddNoteActivity.class);
            intent.putExtra("type", Type.EDIT.getType());//edit
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("data", note);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }


}
