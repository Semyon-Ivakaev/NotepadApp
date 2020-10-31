package com.example.notepadapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList note_id, note;
    int position, id;
    DataBaseHelper myDb;

    CustomAdapter(Context context, ArrayList note_id, ArrayList note) {
        this.context = context;
        this.note_id = note_id;
        this.note = note;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        myDb = new DataBaseHelper(context);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        this.position = position;
        holder.tv_note_txt.setText(String.valueOf(note.get(position)));
        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createNote = new Intent(context, CreateNote.class);
                createNote.putExtra("id", String.valueOf(note_id.get(position)));
                createNote.putExtra("note", String.valueOf(note.get(position)));
                context.startActivity(createNote);
            }
        });

        holder.iv_delete_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNote(String.valueOf(note_id.get(position))); // лучше так передавать параметр, иначе ломаются айдишники и удаляются рандомные объекты
            }
        });
    }

    @Override
    public int getItemCount() {
        return note_id.size();
    }

    public void deleteNote(String id) {
        myDb.deleteData(id); // для того, чтобы удалить элемент по его id
        Toast.makeText(context, "Напоминание успешно удалено.", Toast.LENGTH_SHORT).show();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rowLayout;
        TextView tv_note_txt;
        ImageView iv_delete_note;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_note_txt = itemView.findViewById(R.id.tv_note_txt);
            rowLayout = itemView.findViewById(R.id.ll_row);
            iv_delete_note = itemView.findViewById(R.id.iv_delete_note);
        }
    }
}
