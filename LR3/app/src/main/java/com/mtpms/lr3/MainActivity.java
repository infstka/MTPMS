package com.mtpms.lr3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Band band;
    private List<Band> bands;
    private ArrayAdapter<Band> adapter;
    private ListView bandlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getDisplayMetrics().widthPixels>getResources().getDisplayMetrics().heightPixels)
        {
            setContentView(R.layout.first_landscape);
        }
        else
        {
            setContentView(R.layout.first_portrait);
        }

        bands = json.importFromJSON(this);
        if(bands == null)
        {
            bands = new ArrayList<>();
        }

        //список групп
        bandlist = findViewById(R.id.bandlist);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bands);
        bandlist.setAdapter(adapter);

        bandlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //открытие выбранного элемента из списка
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                band = bands.get(position);
                loadBandInf();
            }
        });
    }

    //открытие 4 активити (полная информация о группе)
    public void loadBandInf() {
        try
        {
            Intent intnt = new Intent(this, FourthActivity.class);
            intnt.putExtra("band", band);
            startActivity(intnt);
        }
        catch(Exception exc)
        {
            Log.d("LR3", exc.getMessage());
        }
    }

    //кнопка добавления (переход к 2 активти)
    public void add(View view)
    {
        Intent intent = new Intent(this, SecondActivity.class);

        startActivity(intent);
    }
}