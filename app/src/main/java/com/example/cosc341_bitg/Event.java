package com.example.cosc341_bitg;

import java.time.LocalDateTime;

public class Event {
    public String name;
    public int attendees;
    public String location;
    public String date;
    public String description;

    public Event(String name, int attendees, String location, String date, String description) {
        this.name = name;
        this.attendees = attendees;
        this.location = location;
        this.date = date;
        this.description = description;
    }
}
