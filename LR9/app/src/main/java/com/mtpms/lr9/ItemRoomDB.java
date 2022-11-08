package com.mtpms.lr9;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Database;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//держатель БД, что служит точкой доступа к ней, использует ДАО для запросов
@Database(entities = {BassItem.class}, version = 1, exportSchema = false)
abstract class ItemRoomDB extends RoomDatabase {

    abstract ItemInterface ii();

    private static volatile ItemRoomDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ItemRoomDB getDB(final Context context) {
        if (INSTANCE == null) {
            synchronized (ItemRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ItemRoomDB.class, "LR9.db").addCallback(cb).build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback cb = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                ItemInterface itint = INSTANCE.ii();
                itint.deleteAll();

                BassItem example = new BassItem(null, "TestBass", "0", "0");
                itint.insert(example);

            });
        }
    };
}
