package com.umbrella.mynotes;

import java.util.ArrayList;
import java.util.Date;

public class NotesRepository {

    public static final NotesRepository INSTANCE = new NotesRepository();

    private final ArrayList<Note> notes = new ArrayList<>();

    public NotesRepository() {
        notes.add(new Note(1, "Стоматолог", "В 11 часов сходить к стоматологу", "Вторник"));
        notes.add(new Note(2, "Парикмахер", "В 15 часов сходить к парикмахеру", "Воскресенье"));
        notes.add(new Note(3, "День рождения", "Вечером пойти на ДР", "Суббота"));
        notes.add(new Note(4, "Фильм", "В 17 часов сходить в кино", "Пятница"));
        notes.add(new Note(5, "Покупки", "Купить хлеб и молоко", "Среда"));
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void changeNote(Note note) {
        for (int i = 0; i < notes.size(); i++) {
            Note item = notes.get(i);
            if (item.getId() == (note.getId())) {
                notes.remove(i);
                notes.add(i, note);
            }
        }
    }
}