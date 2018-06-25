package com.example.example.demolevel5;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Serializable that is made for a quote of the day item.
 * The SerializedName and Expose annotations from Gson are used.
 * <p>
 * SerializedName: The string given to this annotation gives the name that is used for serialising and deserializing
 * Expose: Indicates this variable must be exposed for serialising and deserializing
 */
public class DayQuoteItem implements Serializable {
    @SerializedName("text")
    @Expose
    private String text;


    public DayQuoteItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
