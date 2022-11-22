package com.mtpms.lr9;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

//объект доступа к данным. Отображение запросов SQL на функции

public class ItemRepository {

    private ItemInterface ii;
    private LiveData<List<BassItem>> AllItems;

    ItemRepository(Application application)
    {
        ItemRoomDB db = ItemRoomDB.getDB(application);
        ii = db.ii();
        AllItems = ii.getAlphabetizedItems();
    }

    //класс хранителя данных, что можно наблюдать. Хранит последнюю версию данных
    //помещение в него и получение из него объектов
    LiveData<List<BassItem>> getAllItems()
    {
        return AllItems;
    }

    void insert(BassItem item)
    {
        ItemRoomDB.databaseWriteExecutor.execute(() -> {ii.insert(item);});
    }

    void delete(BassItem item)
    {
        ItemRoomDB.databaseWriteExecutor.execute(() -> {ii.delete(item);});
    }
}
