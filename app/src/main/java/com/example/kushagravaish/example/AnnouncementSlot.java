package com.example.kushagravaish.example;

/**
 * Created by Kushagra Vaish on 7/26/2016.
 */

public class AnnouncementSlot {
    String timings;
    String firstGuy;
    String secondGuy;

    public AnnouncementSlot(String secondGuy, String timings, String firstGuy) {
        this.secondGuy = secondGuy;
        this.timings = timings;
        this.firstGuy = firstGuy;
    }
}
