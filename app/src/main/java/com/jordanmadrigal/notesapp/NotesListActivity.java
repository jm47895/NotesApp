package com.jordanmadrigal.notesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.LinearInterpolator;

import com.jordanmadrigal.notesapp.adapters.NotesAdapter;
import com.jordanmadrigal.notesapp.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesListActivity extends AppCompatActivity {

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
        adapter = new NotesAdapter(notes);
        recyclerView.setAdapter(adapter);

        testData();
    }

    private void testData(){
        for(int i = 0; i < 20; i++) {
            Note note = new Note();
            note.setTitle("This is title " + i);
            note.setTimeStamp("Jan " + i);
            notes.add(note);
        }

        adapter.notifyDataSetChanged();
    }
}
