package com.umbrella.mynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private List<Note> notes;
    private OnNoteClickListener onNoteClickListener;
    private OnNoteLongClickListener onNoteLongClickListener;
    private Fragment fragment;

    public NotesAdapter(List<Note> notes, Fragment fragment) {
        this.notes = notes;
        this.fragment = fragment;
    }

    public interface OnNoteClickListener {
        void onNoteClick(Note note);
    }

    public interface OnNoteLongClickListener {
        void onNoteLongClick(Note note, int index);
    }

    public OnNoteLongClickListener getOnNoteLongClickListener() {
        return onNoteLongClickListener;
    }

    public void setOnNoteLongClickListener(OnNoteLongClickListener onNoteLongClickListener) {
        this.onNoteLongClickListener = onNoteLongClickListener;
    }

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    public OnNoteClickListener getOnNoteClickListener() {
        return onNoteClickListener;
    }

    public void remove(Note longClickedNote) {
        notes.remove(longClickedNote);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView dayOfWeek;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fragment.registerForContextMenu(itemView);
            title = itemView.findViewById(R.id.item_note_title);
            dayOfWeek = itemView.findViewById(R.id.item_note_day_of_week);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNoteClickListener != null) {
                        onNoteClickListener.onNoteClick(notes.get(getAdapterPosition()));
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemView.showContextMenu();
                    if (getOnNoteLongClickListener() != null) {
                        int index = getAdapterPosition();
                        onNoteLongClickListener.onNoteLongClick(notes.get(index), index);
                    }
                    return true;
                }
            });
        }

        public void bind(Note note) {
            dayOfWeek.setText(note.getDayOfWeek());
            title.setText(note.getTitle());
        }
    }
}
