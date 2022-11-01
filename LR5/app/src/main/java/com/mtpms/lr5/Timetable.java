package com.mtpms.lr5;

import static com.mtpms.lr5.R.id.fullD;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Timetable  extends AppCompatActivity {
    String[] Days = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
    private String day;

    String[] Weeks = {"1 неделя", "2 неделя"};
    private String currentWeek;
    private int currentNumberOfWeek;

    private List<Lesson> lessons;
    private ArrayAdapter<Lesson> adapter;
    private ListView fulltt;

    private Lesson lesson;
    private List<Lesson> todayLes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);

        Spinner days = findViewById(fullD);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        days.setAdapter(adapter);

        Spinner week = findViewById(R.id.fullW);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Weeks);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        week.setAdapter(adapter);

        AdapterView.OnItemSelectedListener isld = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                day = (String)adapterView.getItemAtPosition(i);
                changeLng();
                loadlist();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        days.setOnItemSelectedListener(isld);

        AdapterView.OnItemSelectedListener islw = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentWeek = (String)adapterView.getItemAtPosition(i);
                if(currentWeek == Weeks[0])
                {
                    currentNumberOfWeek = 1;
                }
                else
                {
                    currentNumberOfWeek = 2;
                }
                loadlist();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        week.setOnItemSelectedListener(islw);

        fulltt = findViewById(R.id.full);
        fulltt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lesson = todayLes.get(i);
                loadLessonInfo();
            }
        });
    }

    private void loadLessonInfo()
    {
        try
        {
            Intent int1 = new Intent(this, LessonInf.class);
            int1.putExtra("les", lesson);
            startActivity(int1);
        }
        catch(Exception exc)
        {
            Log.d("LR5", exc.getMessage());
        }
    }

    private void loadlist()
    {
        todayLes = new ArrayList<>();
        lessons = JSON.importFromJSON(this);
        if(lessons == null)
        {
            lessons = new ArrayList<>();
        }
        for(Lesson lesson:lessons)
        {
            if(lesson.Day.equals(day) && lesson.Week == currentNumberOfWeek)
            {
                todayLes.add(lesson);
            }
        }
        ArrayAdapter<Lesson> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todayLes);
        ListView lw = findViewById(R.id.full);
        lw.setAdapter(adapter);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
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
}
