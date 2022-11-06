package com.mtpms.lr7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper
{
    private static final String DB_NAME = "LR7.db";
    private static final int DB_VERSION = 1;
    static final String DB_TABLE = "Lessons";

    public static final String ID_COL = "id";
    public static final String NAME_COL = "name";
    public static final String TIME_COL = "time";
    public static final String AUD_COL = "aud";
    public static final String LECTOR_COL = "lector";
    public static final String DAY_COL = "day";
    public static final String WEEK_COL = "week";

    public DB(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE Lessons " +
                "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME_COL + " TEXT," +
                TIME_COL + " TEXT," +
                AUD_COL + " TEXT," +
                LECTOR_COL + " TEXT," +
                DAY_COL + " TEXT," +
                WEEK_COL + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV)
    {
     db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
     onCreate(db);
    }
}
