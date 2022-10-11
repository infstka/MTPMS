package com.mtpms.lr4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

        spinner = findViewById(R.id.choosegenre);
        ArrayAdapter<?> genreAdapter = ArrayAdapter.createFromResource(this, R.array.genres, android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(genreAdapter);
    }

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

    public void thrdNext(View view)
    {
        DatePicker date = findViewById(R.id.enterdate);
        EditText nor = findViewById(R.id.enterNOR);
        EditText nom = findViewById(R.id.enterNOM);

        String slctdGenre = spinner.getSelectedItem().toString();
        String NOR = nor.getText().toString();
        String NOM = nom.getText().toString();
        String chsDate = date.getDayOfMonth() + "." + date.getMonth() + "." + date.getYear();
        String bndName = getIntent().getStringExtra("bndName");
        String img = getIntent().getStringExtra("img");
        String sEmail = getIntent().getStringExtra("sEmail");
        String sPhone = getIntent().getStringExtra("sPhone");
        String sLink = getIntent().getStringExtra("sLink");

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("slctdGenre", slctdGenre);
        intent.putExtra("NOR", NOR);
        intent.putExtra("NOM", NOM);

        Band band = new Band(bndName, img, sEmail, sPhone, sLink, slctdGenre, NOR, NOM, chsDate);

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