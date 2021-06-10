package com.umbrella.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;

public class NoteDescriptionActivity extends AppCompatActivity {

    public static final String ARG_NOTE = "ARG_NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_description);

        if (savedInstanceState == null) {
            Note note = getIntent().getParcelableExtra(ARG_NOTE);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, NotesDescriptionFragment.newInstance(note)).commit();
        }
    }
}