package com.jordanmadrigal.notesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jordanmadrigal.notesapp.models.Note;
import com.jordanmadrigal.notesapp.utils.LinedEditText;

import static com.jordanmadrigal.notesapp.utils.Constants.NOTE_CONTENT_KEY;

public class NoteActivity extends AppCompatActivity implements
        View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final String TAG = "NoteActivity";

    private LinedEditText linedEditText;
    private EditText editTitle;
    private TextView textTitle;
    private Note initialNote;
    private boolean isNewNote;
    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        linedEditText = findViewById(R.id.note_body_edit);
        editTitle = findViewById(R.id.note_edit_title);
        textTitle = findViewById(R.id.note_text_title);

        if(isNewNoteIntent()){
            setNewNoteProperties();
        }else{
            setExistingNoteProperties();
        }

        setListeners();

    }

    private void setListeners(){
        linedEditText.setOnTouchListener(this);
        gestureDetector = new GestureDetector(this, this);
    }

    private void setExistingNoteProperties() {
        textTitle.setText(initialNote.getTitle());
        editTitle.setText(initialNote.getTitle());
        linedEditText.setText(initialNote.getContent());
    }

    private void setNewNoteProperties() {
        textTitle.setText(R.string.note_title);
        editTitle.setText(R.string.note_title);
    }

    private boolean isNewNoteIntent() {

        if(getIntent().hasExtra(NOTE_CONTENT_KEY)){
            initialNote = getIntent().getParcelableExtra(NOTE_CONTENT_KEY);
            Log.d(TAG, "isNewNote: " + initialNote.toString());
            isNewNote =false;

            return false;
        }
        isNewNote = true;

        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d(TAG, "onDoubleTapEvent: double tap");
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
