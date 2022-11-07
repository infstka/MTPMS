package com.mtpms.lr8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.mtpms.lr8.ui.home.StateAdapter;

import java.util.ArrayList;
import java.util.List;

public class CreateBass extends AppCompatActivity {

    private List<Bass> basses;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSION_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity)
    {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(activity, PERMISSION_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bass);

        verifyStoragePermissions(this);

        RecyclerView rv = findViewById(R.id.list);

        try
        {
            basses = JSON.importFromJSON(this);

            if(basses == null)
            {
                basses = new ArrayList<>();
            }
        }
        catch(Exception exc)
        {

        }

        int spanCount = 2;
        int spacing = 5;
        boolean includeEdge = true;
        StateAdapter sa = new StateAdapter(this, basses);
        GridLayoutManager glm = new GridLayoutManager(this, 2);

        rv.setLayoutManager(glm);
        rv.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        rv.setAdapter(sa);
    }
}