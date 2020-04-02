package com.jordanmadrigal.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.animation.LinearInterpolator;

import com.jordanmadrigal.notesapp.adapters.NotesAdapter;
import com.jordanmadrigal.notesapp.models.Note;

import java.util.ArrayList;
import java.util.List;

import static com.jordanmadrigal.notesapp.utils.Constants.NOTE_CONTENT_KEY;

public class NotesListActivity extends AppCompatActivity implements NotesAdapter.OnNoteListener {

    private static final String TAG = "NotesListActivity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        recyclerView = findViewById(R.id.notes_recyclerView);

        initRecyclerView();

        setSupportActionBar((Toolbar) findViewById(R.id.notes_toolbar));
        setTitle("Notes");
    }

    private void initRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NotesAdapter(notes, this);
        recyclerView.setAdapter(adapter);

        testData();
    }

    private void testData(){
        for(int i = 0; i < 20; i++) {
            Note note = new Note();
            note.setTitle("This is title " + i);
            note.setTimeStamp("Jan " + i);
            note.setContent("content");
            notes.add(note);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: " + position);
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra(NOTE_CONTENT_KEY, notes.get(position));
        startActivity(intent);
    }
}
