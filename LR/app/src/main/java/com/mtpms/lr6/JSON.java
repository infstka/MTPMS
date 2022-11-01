package com.mtpms.lr6;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class JSON
{
    private static final String FILE_NAME = "LR6.json";

    static boolean exportToJSON(Context context, List<Lesson> dataList)
    {
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setLessons(dataList);
        String jsonString = gson.toJson(dataItems);

        try(FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE))
        {
            fos.write(jsonString.getBytes());
            return true;
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
        return false;
    }

    static List<Lesson> importFromJSON(Context context)
    {
        try(FileInputStream fis = context.openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis))
        {
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(isr, DataItems.class);
            return dataItems.getLessons();
        }
        catch (IOException ioexc)
        {
            ioexc.printStackTrace();
        }
        return null;
    }

    private static class DataItems
    {
        private List<Lesson> llessons;
        List<Lesson> getLessons()
        {
            return llessons;
        }
        void setLessons(List<Lesson> llessons)
        {
            this.llessons = llessons;
        }
    }
}
