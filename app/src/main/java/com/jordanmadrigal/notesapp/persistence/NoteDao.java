package com.jordanmadrigal.notesapp.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jordanmadrigal.notesapp.models.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    long[] insertNote(Note... notes);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getNotes();

    @Query("SELECT * FROM notes WHERE title LIKE :title")
    List<Note> getNoteWithCustomQuery(String title);

    @Delete
    int deleteNote(Note... notes);

    @Update
    int updateNote(Note... notes);
}
