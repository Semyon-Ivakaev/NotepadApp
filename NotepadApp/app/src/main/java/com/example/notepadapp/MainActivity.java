package com.example.notepadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper myDb;
    private RecyclerView recyclerView;
    private FloatingActionButton addNewNoteButton;
    private ArrayList<String> notes, notes_id;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DataBaseHelper(this);
        notes_id = new ArrayList<>();
        notes = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_show_all_notes);
        addNewNoteButton = findViewById(R.id.fab);

        storeDataInArray(); // метод добавляет все, что есть в базе в список notes

        customAdapter = new CustomAdapter(this, notes_id, notes);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addNewNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Intent addNewNote = new Intent(context, AddNewNote.class);
                startActivity(addNewNote);
            }
        });
    }

    void storeDataInArray() {
        Cursor res = myDb.getAllData();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                notes_id.add(res.getString(0));
                notes.add(res.getString(1));
            }
            Toast.makeText(this, "Все напоминания загружены.", Toast.LENGTH_SHORT).show();
            }
    }
}