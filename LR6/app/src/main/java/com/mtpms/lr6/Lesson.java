package com.mtpms.lr6;

import java.io.Serializable;

public class Lesson implements Comparable<Lesson>, Serializable {

    public String name;
    public String aud;
    public String lector;
    public String time;

    Lesson(String name, String aud, String lector, String time)
    {
        this.name = name;
        this.aud = aud;
        this.lector = lector;
        this.time = time;
    }

    @Override
    public int compareTo(Lesson lesson) {
        return name.compareTo(lesson.name);
    }
}
