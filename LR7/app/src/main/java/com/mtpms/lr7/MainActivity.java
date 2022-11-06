package com.mtpms.lr7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int IDM_OPEN = 101;
    public static final int IDM_SAVE = 102;

    String[] Weeks = {"1 неделя","2 неделя"};
    private String currentWeek;
    private int currentNumberOfWeek;
    private List<Lesson> lessons;
    private ArrayAdapter<Lesson> adapter;
    private String dayOfWeek;

    SQLiteDatabase sql;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(getApplicationContext());

        setTitle("Timetable");

        Spinner week = findViewById(R.id.week);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Weeks);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        week.setAdapter(adapter);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        dayOfWeek = sdf.format(d);
        TextView current = findViewById(R.id.current);
        current.setText(dayOfWeek);

        currentNumberOfWeek = 1;

        LoadTimetable();

        AdapterView.OnItemSelectedListener isl = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentWeek = (String)adapterView.getItemAtPosition(i);

                if (currentWeek == Weeks[0])
                {
                    currentNumberOfWeek = 1;
                }
                else
                {
                    currentNumberOfWeek = 2;
                }
                LoadTimetable();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        week.setOnItemSelectedListener(isl);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sql = db.getReadableDatabase();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, IDM_OPEN, Menu.NONE, "Открыть");
        menu.add(Menu.NONE, IDM_SAVE, Menu.NONE, "Сохранить");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        CharSequence cs;
        switch (item.getItemId())
        {
            case IDM_OPEN: cs = "Выбрано Открыть";
                    break;
            case IDM_SAVE: cs = "Выбрано Сохранить";
                    break;
            default: return super.onContextItemSelected(item);
        }
        Toast t = Toast.makeText(this, cs, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch(id)
        {
            case R.id.addd: Intent CreateItem = new Intent(this, NewItem.class);
            startActivity(CreateItem);
            return true;

            case R.id.timetable: Intent Timetable = new Intent(this, Timetable.class);
            startActivity(Timetable);
            return true;

            case R.id.up: Timetable = new Intent(this, Timetable.class);
            startActivity(Timetable);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        this.finishAffinity();
    }

    public void LoadTimetable()
    {
        List<Lesson> todayLes = new ArrayList<>();

        lessons = JSON.importFromJSON(this);

        if(lessons == null)
        {
            lessons = new ArrayList<>();
        }

        for(Lesson lesson:lessons)
        {
            if(lesson.Day.equals(dayOfWeek) && lesson.Week == currentNumberOfWeek)
            {
                todayLes.add(lesson);
            }

            ArrayAdapter<Lesson> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lessons);
            ListView lw = findViewById(R.id.timetable);
            lw.setAdapter(adapter);
            registerForContextMenu(lw);
        }
    }
}