package com.mtpms.lr4;

import java.io.Serializable;

class Band implements Serializable
{
    public String BandName;
    public String Logo;
    public String Email;
    public String Phone;
    public String Link;
    public String Genre;
    public String NumberOfReleases;
    public String NumberOfMembers;
    public String Date;

    Band(String bandName, String logo, String email, String phone, String link, String genre, String nor, String nom, String date)
    {
        BandName = bandName;
        Logo = logo;
        Email = email;
        Phone = phone;
        Link = link;
        Genre = genre;
        NumberOfReleases = nor;
        NumberOfMembers = nom;
        Date = date;
    }

    public String toString()
    {
        return "Название группы: " + BandName + "\nДата основания: " + Date + "\nЖанр: " +
                Genre + "\nКоличество релизов: " +
                NumberOfReleases + "\nКоличество участников: " + NumberOfMembers +
                "\nПочта: " + Email + "\nТелефон: " + Phone + "\nСсылка на соцсеть: " + Link;
    }
}
