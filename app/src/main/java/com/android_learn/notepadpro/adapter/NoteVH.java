package com.android_learn.notepadpro.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.android_learn.notepadpro.R;

public class NoteVH extends RecyclerView.ViewHolder {
    AppCompatTextView txt_title;
    AppCompatTextView txt_description;
    AppCompatTextView txt_date;
    AppCompatTextView txt_time;
    AppCompatImageView img_delete;
    AppCompatImageView img_share;
    AppCompatImageView img_edit;
    public NoteVH(@NonNull View itemView) {
        super(itemView);
         txt_title = itemView.findViewById(R.id.txt_title);
         txt_description = itemView.findViewById(R.id.txt_description);
         txt_date = itemView.findViewById(R.id.txt_date);
         txt_time = itemView.findViewById(R.id.txt_time);
         img_delete = itemView.findViewById(R.id.img_delete);
         img_share = itemView.findViewById(R.id.img_share);
         img_edit = itemView.findViewById(R.id.img_edit);
    }


}
