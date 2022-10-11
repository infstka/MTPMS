package com.mtpms.lr3;

import java.io.Serializable;

class Band implements Serializable
{
    public String BandName;
    public String Logo;
    public String Genre;
    public String NumberOfReleases;
    public String NumberOfMembers;
    public String Date;

    Band(String bandName, String logo, String genre, String nor, String nom, String date)
    {
        BandName = bandName;
        Logo = logo;
        Genre = genre;
        NumberOfReleases = nor;
        NumberOfMembers = nom;
        Date = date;
    }

    //отображение в ListView
    public String toString()
    {
        return "Название группы: " + BandName + "\nДата основания: " + Date + "\nЖанр: " +
                Genre + "\nКоличество релизов: " +
                NumberOfReleases + "\nКоличество участников: " + NumberOfMembers;
    }
}
