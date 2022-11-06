package com.mtpms.lr7;

import java.io.Serializable;
import java.util.Objects;

public class Lesson implements Serializable
{
    public String Name;
    public String Time;
    public String Aud;
    public String Lector;
    public String Day;
    public int Week;

    public Lesson(String name, String time, String aud, String lector, String day, int week)
    {
        Name = name;
        Time = time;
        Aud = aud;
        Lector = lector;
        Day = day;
        Week = week;
    }

    @Override
    public String toString()
    {
        return Name + "\n" + Aud + "\n" + Time;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Lesson lesson = (Lesson) obj;
        return Week == lesson.Week && Objects.equals(Name, lesson.Name) && Objects.equals(Time, lesson.Time)
                && Objects.equals(Aud, lesson.Aud) && Objects.equals(Lector, lesson.Lector)
                && Objects.equals(Day, lesson.Day);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(Name, Time, Aud, Lector, Day, Week);
    }
}
