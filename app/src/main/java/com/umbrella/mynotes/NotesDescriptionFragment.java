package com.umbrella.mynotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotesDescriptionFragment extends Fragment {

    private static final String ARG_NOTE = "ARG_NOTE";

    public static NotesDescriptionFragment newInstance(Note note) {
        NotesDescriptionFragment fragment = new NotesDescriptionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    public NotesDescriptionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView title = view.findViewById(R.id.note_title);
        TextView description = view.findViewById(R.id.note_description);
        TextView dayOfWeek = view.findViewById(R.id.note_day_of_week);

        Note note = getArguments().getParcelable(ARG_NOTE);

        title.setText(note.getTitle());
        description.setText(note.getDescription());
        dayOfWeek.setText(note.getDayOfWeek());
    }
}