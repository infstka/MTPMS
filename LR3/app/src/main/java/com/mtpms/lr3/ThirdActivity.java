package com.mtpms.lr3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    private List<Band> Bands;
    private ArrayAdapter<Band> adapter;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getDisplayMetrics().widthPixels>getResources().getDisplayMetrics().heightPixels)
        {
            setContentView(R.layout.third_landscape);
        }
        else
        {
            setContentView(R.layout.third_portrait);
        }

        //список в спиннере
        spinner = findViewById(R.id.choosegenre);
        ArrayAdapter<?> genreAdapter = ArrayAdapter.createFromResource(this, R.array.genres, android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(genreAdapter);
    }

    //сохранение данных в JSON
    private void saveJSON()
    {
        boolean result = json.exportToJSON(this, Bands);
        if(result)
        {
            Toast.makeText(this, "Сохранено!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Не сохранено!", Toast.LENGTH_LONG).show();
        }
    }

    //кнопка далее (переход в 1 активити)
    public void thrdNext(View view)
    {
        DatePicker date = findViewById(R.id.enterdate);
        EditText nor = findViewById(R.id.enterNOR);
        EditText nom = findViewById(R.id.enterNOM);

        //придаем ключи значениям
        String slctdGenre = spinner.getSelectedItem().toString();
        String NOR = nor.getText().toString();
        String NOM = nom.getText().toString();
       String chsDate = date.getDayOfMonth() + "." + date.getMonth() + "." + date.getYear();
        String bndName = getIntent().getStringExtra("bndName");
        String img = getIntent().getStringExtra("img");

        //передаем в 1 активити значения
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("slctdGenre", slctdGenre);
        intent.putExtra("NOR", NOR);
        intent.putExtra("NOM", NOM);

        Band band = new Band(bndName, img, slctdGenre, NOR, NOM, chsDate);

        //получение данных из JSON
        Bands = json.importFromJSON(this);
        if(Bands == null)
        {
            Bands = new ArrayList<>();
        }
        Bands.add(band);
        saveJSON();

        ComponentName cn = intent.getComponent();
        Intent.makeRestartActivityTask(cn);
        startActivity(intent);
    }

    public void thrdBack(View view)
    {
        this.finish();
    }
}