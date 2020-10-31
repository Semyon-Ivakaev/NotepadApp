package com.example.notepadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewNote extends AppCompatActivity {
    private Button addNewNoteButton;
    private EditText newNoteText;
    DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        myDb = new DataBaseHelper(this);
        newNoteText = findViewById(R.id.et_add_new_note);
        addNewNoteButton = findViewById(R.id.b_add_new_note);

        addNewNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewNote();
                Context context = AddNewNote.this;
                Intent back = new Intent(context, MainActivity.class);
                startActivity(back);
            }
        });
    }

    public void createNewNote() {
        String text = newNoteText.getText().toString();
        Boolean result = myDb.insertData(text);
        if (result) {
            Toast.makeText(this, "Добавленно!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Ошибка при добавлении!", Toast.LENGTH_LONG).show();
        }
    }
}