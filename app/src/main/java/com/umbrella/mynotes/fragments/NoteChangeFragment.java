package com.umbrella.mynotes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.umbrella.mynotes.Note;
import com.umbrella.mynotes.NotesRepository;
import com.umbrella.mynotes.R;


public class NoteChangeFragment extends Fragment {

    private static final String ARG_NOTE = "ARG_NOTE";
    private NotesRepository repository = NotesRepository.INSTANCE;

    public NoteChangeFragment() {
        // Required empty public constructor
    }

    public static NoteChangeFragment newInstance(Note note) {
        NoteChangeFragment fragment = new NoteChangeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.button_save_changes);
        EditText title = view.findViewById(R.id.edit_note_title);
        EditText description = view.findViewById(R.id.edit_note_description);
        EditText dayOfWeek = view.findViewById(R.id.edit_note_day_of_week);

        Note note = getArguments().getParcelable(ARG_NOTE);
        title.setText(note.getTitle());
        description.setText(note.getDescription());
        dayOfWeek.setText(note.getDayOfWeek());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setTitle(title.getText().toString());
                note.setDayOfWeek(dayOfWeek.getText().toString());
                note.setDescription(description.getText().toString());
                repository.changeNote(note);
                getParentFragmentManager().popBackStack();
            }
        });
    }
}