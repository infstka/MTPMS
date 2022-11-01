package com.mtpms.lr6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NewItem extends AppCompatActivity {
    String[] Days = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
    private String day;
    private int week;
    private List<Lesson> lessons;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);

        Spinner days = findViewById(R.id.day);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        days.setAdapter(adapter);

        AdapterView.OnItemSelectedListener isl = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                day = (String)adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        days.setOnItemSelectedListener(isl);
    }

    public void changeLng()
    {
        switch(day)
        {
            case "Понедельник":
            {
                day = "Monday";
                break;
            }
            case "Вторник":
            {
                day = "Tuesday";
                break;
            }
            case "Среда":
            {
                day = "Wednesday";
                break;
            }
            case "Четверг":
            {
                day = "Thursday";
                break;
            }
            case "Пятница":
            {
                day = "Friday";
                break;
            }
            case "Суббота":
            {
                day = "Saturday";
                break;
            }
            case "Воскресенье":
            {
                day = "Sunday";
                break;
            }
        }
    }

    public void save(View view)
    {
        EditText ename = findViewById(R.id.name);
        EditText etime = findViewById(R.id.time);
        EditText eaud = findViewById(R.id.aud);
        EditText elector = findViewById(R.id.lector);

        String name = ename.getText().toString();
        if(name.length() < 2)
        {
            Toast.makeText(this, "Недостаточная длина", Toast.LENGTH_SHORT).show();
            ename.setText("");
            return;
        }

        String time = etime.getText().toString();
        if(time.length() < 1)
        {
            Toast.makeText(this, "Недостаточная длина", Toast.LENGTH_SHORT).show();
            etime.setText("");
            return;
        }

        String aud = eaud.getText().toString();
        if(aud.length() < 1)
        {
            Toast.makeText(this, "Недостаточная длина", Toast.LENGTH_SHORT).show();
            eaud.setText("");
            return;
        }

        String lector = elector.getText().toString();
        if(lector.length() < 2)
        {
            Toast.makeText(this, "Недостаточная длина", Toast.LENGTH_SHORT).show();
            elector.setText("");
            return;
        }

        changeLng();

        Lesson lesson = new Lesson(name, time, aud, lector, day, week);
        lessons = JSON.importFromJSON(this);
        if(lessons == null)
        {
            lessons = new ArrayList<>();
        }
        lessons.add(lesson);
        JSON.exportToJSON(this, lessons);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId())
        {
            case R.id.week1: if (checked)
            {
                week = 1;
            }
            break;
            case R.id.week2: if (checked)
            {
                week = 2;
            }
            break;
            default: week = 1;
        }
    }

    public void back(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}
