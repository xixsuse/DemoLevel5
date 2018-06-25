package com.example.example.demolevel5;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marmm on 10/25/17.
 */

@Entity(tableName = "reminder")
public class Reminder implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "remindertext")
    private String mReminderText;

    public Reminder(String mReminderText) {
        this.mReminderText = mReminderText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReminderText() {
        return mReminderText;
    }

    public void setReminderText(String reminderText) {
        this.mReminderText = reminderText;
    }

    @Override
    public String toString() {
        return mReminderText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mReminderText);
    }

    protected Reminder(Parcel in) {
        this.mReminderText = in.readString();
    }

    public static final Parcelable.Creator<Reminder> CREATOR = new Parcelable.Creator<Reminder>() {
        @Override
        public Reminder createFromParcel(Parcel source) {
            return new Reminder(source);
        }

        @Override
        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };
}
