package com.mtpms.lr9;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao //объект доступа к данным. Отображение запросов SQL на функции
public interface ItemInterface {

    @Query("SELECT * FROM items_table ORDER BY name ASC")
    LiveData<List<BassItem>> getAlphabetizedItems();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BassItem item);

    @Query("DELETE FROM items_table")
    void deleteAll();

    @Delete
    void delete(BassItem item);
}
