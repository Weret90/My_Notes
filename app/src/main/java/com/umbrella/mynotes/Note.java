package com.umbrella.mynotes;

public class Note {

    private String title;
    private String description;
    private String dayOfWeek;

    public Note(String title, String description, String dayOfWeek) {
        this.title = title;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

}
