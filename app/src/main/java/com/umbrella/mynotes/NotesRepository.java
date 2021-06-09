package com.umbrella.mynotes;

import java.util.ArrayList;

public class NotesRepository {

    public static ArrayList<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("Стоматолог", "В 11 часов сходить к стоматологу", "Вторник"));
        notes.add(new Note("Парикмахер", "В 15 часов сходить к парикмахеру", "Воскресенье"));
        notes.add(new Note("День рождения", "Вечером пойти на ДР", "Суббота"));
        notes.add(new Note("Фильм", "В 17 часов сходить в кино", "Пятница"));
        notes.add(new Note("Покупки", "Купить хлеб и молоко", "Среда"));
        return notes;
    }
}