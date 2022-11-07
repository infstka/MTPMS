package com.mtpms.lr8;

public class Bass {
    public String Name;
    public String Strings;
    public String Pickups;
    public String Img;

    public Bass(String name, String strings, String pickups, String img)
    {
        Name = name;
        Strings = strings;
        Pickups = pickups;
        Img = img;
    }

    public String getName()
    {
        return this.Name;
    }

    public void setName(String name)
    {
        this.Name = name;
    }

    public String getStrings()
    {
        return this.Strings;
    }

    public void setStrings(String strings)
    {
        this.Strings = strings;
    }

    public String getPickups()
    {
        return this.Pickups;
    }

    public void setPickups(String pickups)
    {
        this.Pickups = pickups;
    }

    public String getImg()
    {
        return this.Img;
    }

    public void setImg(String img)
    {
        this.Img = img;
    }
}
