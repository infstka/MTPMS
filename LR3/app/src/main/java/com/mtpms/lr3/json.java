package com.mtpms.lr3;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class json
{
    private static final String FILE_NAME = "data.json";

    static boolean exportToJSON(Context context, List<Band> dataList)
    {
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setBands(dataList);
        String jsonString = gson.toJson(dataItems);

        try(FileOutputStream FOS = context.openFileOutput(FILE_NAME,Context.MODE_PRIVATE))
        {
            FOS.write(jsonString.getBytes());
            return  true;
        }

        catch(Exception exc)
        {
            exc.printStackTrace();
        }

        return  false;
    }

    static List<Band> importFromJSON(Context context)
    {
        try(FileInputStream FIS = context.openFileInput(FILE_NAME);
            InputStreamReader streamReader = new InputStreamReader(FIS))
        {
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);

            return dataItems.getBands();
        }

        catch(IOException ioexc)
        {
            ioexc.printStackTrace();
        }

        return null;
    }

    private static class DataItems
    {
        private List<Band> bands;

        List<Band> getBands()
        {
            return bands;
        }
        void setBands(List<Band> bands)
        {
            this.bands = bands;
        }
    }
}
