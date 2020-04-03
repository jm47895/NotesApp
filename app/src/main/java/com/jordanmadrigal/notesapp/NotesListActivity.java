package com.jordanmadrigal.notesapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.jordanmadrigal.notesapp.adapters.NotesAdapter;
import com.jordanmadrigal.notesapp.models.Note;

import java.util.ArrayList;
import java.util.List;

import static com.jordanmadrigal.notesapp.utils.Constants.NOTE_CONTENT_KEY;

public class NotesListActivity extends AppCompatActivity implements NotesAdapter.OnNoteListener, View.OnClickListener {

    private static final String TAG = "NotesListActivity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Note> notes = new ArrayList<>();
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        recyclerView = findViewById(R.id.notes_recyclerView);
        findViewById(R.id.fab).setOnClickListener(this);

        initRecyclerView();

        setSupportActionBar((Toolbar) findViewById(R.id.notes_toolbar));
        setTitle("Notes");
    }

    private void initRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }

    private void deleteNote(Note note){
        notes.remove(note);
        adapter.notifyDataSetChanged();
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            deleteNote(notes.get(viewHolder.getAdapterPosition()));
        }
    };
}
