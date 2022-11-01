package com.mtpms.lr5;

import static com.mtpms.lr5.R.id.day;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class LessonInf extends AppCompatActivity {

    private int week;
    private Lesson Lesson;
    private List<Lesson> lessons;

    String[] Days = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
    private String day;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_info);

        Bundle arg = getIntent().getExtras();
        Lesson = (Lesson) arg.get("les");
        week = Lesson.Week;
        loadInf();

        Spinner days = findViewById(R.id.day);
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

    private void loadInf()
    {
        EditText nname = findViewById(R.id.name);
        EditText ntime = findViewById(R.id.time);
        EditText naud = findViewById(R.id.aud);
        EditText nlector = findViewById(R.id.lector);

        nname.setText(Lesson.Name);
        ntime.setText(Lesson.Time);
        naud.setText(Lesson.Aud);
        nlector.setText(Lesson.Lector);

        Spinner days = findViewById(R.id.day);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        days.setAdapter(adapter);

        changeLngReverse();
        for (int i = 0; i < Days.length; i++)
        {
            if (day.equals(Days[i]))
            {
                days.setSelection(i);
                days.setEnabled(false);
                return;
            }
        }
    }
    public void changeLngReverse()
    {
        switch(Lesson.Day)
        {
            case "Monday":
            {
                day = "Понедельник";
                break;
            }
            case "Tuesday":
            {
                day = "Вторник";
                break;
            }
            case "Wednesday":
            {
                day = "Среда";
                break;
            }
            case "Thursday":
            {
                day = "Четверг";
                break;
            }
            case "Friday":
            {
                day = "Пятница";
                break;
            }
            case "Saturday":
            {
                day = "Суббота";
                break;
            }
            case "Sunday":
            {
                day = "Воскресенье";
                break;
            }
        }
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

    public void delete(View view)
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Вы точно хотите удалить элемент?");
        ad.setMessage("Восстановление будет невозможно");

        ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                delItem();
                backToTT();
            }
        });
        ad.show();
    }

    private void delItem()
    {
        lessons = JSON.importFromJSON(this);
        if(lessons == null)
        {
            lessons = new ArrayList<>();
        }
        for(Lesson lesson : lessons)
        {
            if(lesson.equals(Lesson))
            {
                lessons.remove(lesson);
                break;
            }
        }
        JSON.exportToJSON(this, lessons);
    }

    public void backToTT()
    {
        Intent intent = new Intent(this, Timetable.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void save(View view)
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Вы точно хотите изменить элемент?");
        ad.setMessage("Восстановление будет невозможно");

        ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                delItem();
                addEditItem();
                backToTT();
            }
        });
        ad.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        ad.show();
    }

    private void addEditItem()
    {
        EditText edname = findViewById(R.id.name);
        EditText edtime = findViewById(R.id.time);
        EditText edaud = findViewById(R.id.aud);
        EditText edlector = findViewById(R.id.lector);

        String name = edname.getText().toString();
        String time = edtime.getText().toString();
        String aud = edaud.getText().toString();
        String lector = edlector.getText().toString();

        changeLng();

        Lesson lesson = new Lesson(name, time, aud, lector, day, week);
        lessons = JSON.importFromJSON(this);
        if(lessons == null)
        {
            lessons = new ArrayList<>();
        }
        lessons.add(lesson);
        JSON.exportToJSON(this, lessons);
    }

    public void back(View view)
    {
        Intent intent = new Intent(this, Timetable.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}
