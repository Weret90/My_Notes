package com.umbrella.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NotesFragment.OnNoteClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onNoteClicked(Note note) {
        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);
        if (isLandscape) {
            getSupportFragmentManager().beginTransaction().replace(R.id.note_description_fragment, NotesDescriptionFragment.newInstance(note)).commit();
        } else {
            Intent intent = new Intent(this, NoteDescriptionActivity.class);
            intent.putExtra(NoteDescriptionActivity.ARG_NOTE, note);
            startActivity(intent);
        }
    }
}