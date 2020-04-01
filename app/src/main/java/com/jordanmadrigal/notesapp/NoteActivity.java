package com.jordanmadrigal.notesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jordanmadrigal.notesapp.models.Note;

import static com.jordanmadrigal.notesapp.utils.Constants.NOTE_CONTENT_KEY;

public class NoteActivity extends AppCompatActivity {

    private static final String TAG = "NoteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        if(getIntent().hasExtra(NOTE_CONTENT_KEY)){
            Note note = getIntent().getParcelableExtra(NOTE_CONTENT_KEY);
            Log.d(TAG, "Intent Extra " + note.toString());
        }
    }
}
