package com.mtpms.lr9;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    //предоставляет данные для ПИ. Действует как центр связи между репоzиторием и ПИ

    private ItemRepository ir;
    private final LiveData<List<BassItem>> AllItems;

    public ItemViewModel(Application application)
    {
        super(application);
        ir = new ItemRepository(application);
        AllItems = ir.getAllItems();
    }

    LiveData<List<BassItem>> getAllItems()
    {
        return AllItems;
    }

    void insert(BassItem item)
    {
        ir.insert(item);
    }

    void delete(BassItem item)
    {
        ir.delete(item);
    }
}
