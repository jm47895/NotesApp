package com.jordanmadrigal.notesapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jordanmadrigal.notesapp.models.Note;
import com.jordanmadrigal.notesapp.utils.LinedEditText;

import static com.jordanmadrigal.notesapp.utils.Constants.NOTE_CONTENT_KEY;

public class NoteActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    private static final String TAG = "NoteActivity";
    private static final int EDIT_MODE_ENABLED = 1;
    private static final int EDIT_MODE_DISABLED = 0;

    private LinedEditText linedEditText;
    private EditText editTitle;
    private TextView textTitle;
    private Note initialNote;
    private boolean isNewNote;
    private GestureDetector gestureDetector;
    private ImageButton checkButton, backButton;
    private int mode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        linedEditText = findViewById(R.id.note_body_edit);
        editTitle = findViewById(R.id.note_edit_title);
        textTitle = findViewById(R.id.note_text_title);
        checkButton = findViewById(R.id.note_check_button);
        backButton = findViewById(R.id.note_back_button);

        if(isNewNoteIntent()){
            setNewNoteProperties();
            setEditModeEnabled(true);
        }else{
            setExistingNoteProperties();
            setEditModeEnabled(false);
        }

        setListeners();

    }

    private void setListeners(){
        linedEditText.setOnTouchListener(this);
        textTitle.setOnClickListener(this);
        checkButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
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
            mode = EDIT_MODE_DISABLED;

            return false;
        }

        isNewNote = true;
        mode = EDIT_MODE_ENABLED;

        return true;
    }

    private void setEditModeEnabled(boolean editModeEnabled){
        if(editModeEnabled){
            checkButton.setVisibility(View.VISIBLE);
            editTitle.setVisibility(View.VISIBLE);

            backButton.setVisibility(View.GONE);
            textTitle.setVisibility(View.GONE);

            linedEditText.setCursorVisible(true);
            mode = EDIT_MODE_ENABLED;
        }else{

            hideKeyboard();

            checkButton.setVisibility(View.GONE);
            editTitle.setVisibility(View.GONE);

            backButton.setVisibility(View.VISIBLE);
            textTitle.setVisibility(View.VISIBLE);

            linedEditText.setCursorVisible(false);
            mode = EDIT_MODE_DISABLED;

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        setEditModeEnabled(true);
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.note_check_button:
                setEditModeEnabled(false);
                break;
            case R.id.note_text_title:
                setEditModeEnabled(true);
                editTitle.requestFocus();
                editTitle.setSelection(editTitle.length());
                break;
            case R.id.note_back_button:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(mode == EDIT_MODE_ENABLED){
            onClick(checkButton);
        }else {
            super.onBackPressed();
        }
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view  = this.getCurrentFocus();
        if(view == null){
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
