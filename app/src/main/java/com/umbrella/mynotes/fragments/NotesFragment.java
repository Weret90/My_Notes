package com.umbrella.mynotes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umbrella.mynotes.Note;
import com.umbrella.mynotes.NotesAdapter;
import com.umbrella.mynotes.NotesRepository;
import com.umbrella.mynotes.R;

import java.util.List;

public class NotesFragment extends Fragment {

    private int longClickedIndex;
    private Note longClickedNote;
    private NotesAdapter adapter;
    private NotesRepository repository = NotesRepository.INSTANCE;

    public interface OnNoteClicked {
        void onNoteClicked(Note note);
    }

    private OnNoteClicked onNoteClicked;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNoteClicked) {
            onNoteClicked = (OnNoteClicked) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onNoteClicked = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView notesRecyclerView = view.findViewById(R.id.notes_recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        notesRecyclerView.setLayoutManager(manager);
        List<Note> notes = repository.getNotes();
        adapter = new NotesAdapter(notes, this);
        notesRecyclerView.setAdapter(adapter);
        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                onNoteClicked.onNoteClicked(note);
            }
        });
        adapter.setOnNoteLongClickListener(new NotesAdapter.OnNoteLongClickListener() {
            @Override
            public void onNoteLongClick(Note note, int index) {
                longClickedIndex = index;
                longClickedNote = note;
            }
        });
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_change) {
            getParentFragmentManager().beginTransaction().replace(R.id.notes_titles, NoteChangeFragment.newInstance(longClickedNote)).addToBackStack(null).commit();
            return true;
        }
        if (item.getItemId() == R.id.action_delete) {
            adapter.remove(longClickedNote);
            adapter.notifyItemRemoved(longClickedIndex);
            return true;
        }
        return super.onContextItemSelected(item);
    }
}


