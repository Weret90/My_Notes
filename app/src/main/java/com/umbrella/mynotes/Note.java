package com.umbrella.mynotes;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    private final String title;
    private final String description;
    private final String dayOfWeek;

    public Note(String title, String description, String dayOfWeek) {
        this.title = title;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
    }

    protected Note(Parcel in) {
        title = in.readString();
        description = in.readString();
        dayOfWeek = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(dayOfWeek);
    }
}
