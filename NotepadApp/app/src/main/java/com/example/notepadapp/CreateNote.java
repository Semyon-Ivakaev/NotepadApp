package com.example.notepadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CreateNote extends AppCompatActivity {
    private TextView note_input;
    private Button createNoteButton, deleteNoteButton;
    private String id, note;
    DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        myDb = new DataBaseHelper(this);
        note_input = findViewById(R.id.et_create_note_txt);
        createNoteButton = findViewById(R.id.b_create_note);
        deleteNoteButton = findViewById(R.id.b_delete_note);

        createNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNote();
                Context context = CreateNote.this;
                Intent back = new Intent(context, MainActivity.class);
                startActivity(back);
                Toast.makeText(context, "Напоминание отредактировано.", Toast.LENGTH_SHORT).show();
            }
        });

        deleteNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNote();
                Context context = CreateNote.this;
                Intent back = new Intent(context, MainActivity.class);
                startActivity(back);
                Toast.makeText(context, "Напоминание удалено.", Toast.LENGTH_SHORT).show();
            }
        });
        getAndSetIntentData();
    }

    public void createNote() {
        String text = note_input.getText().toString();
        Boolean result = myDb.updateData(id, text);
    }

    public void deleteNote() {
        Integer result = myDb.deleteData(id);
    }

    public void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("note")) {
            // получаем данные из интента
            id = getIntent().getStringExtra("id");
            note = getIntent().getStringExtra("note");

            // записываем данные в поле ввода
            note_input.setText(note);
        } else {
            Toast.makeText(this, "Нет данных.", Toast.LENGTH_SHORT).show();
        }
    }
}