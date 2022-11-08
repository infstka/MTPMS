package com.mtpms.lr9;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//необходим при работе с архитектурой приложения, описывает таблицу БД
@Entity(tableName = "items_table")
public class BassItem {

    String img;
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "name")
    String name;

    @NonNull
    @ColumnInfo(name = "strings")
    String strings;

    @NonNull
    @ColumnInfo(name = "pickups")
    String pickups;

    BassItem(String img, String name, String strings, String pickups)
    {
        this.img = img;
        this.name = name;
        this.strings = strings;
        this.pickups = pickups;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
