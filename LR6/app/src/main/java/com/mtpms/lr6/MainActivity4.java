package com.mtpms.lr6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    private List<Lesson> lessonList;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Button save = findViewById(R.id.save);
        EditText name = findViewById(R.id.name);
        EditText time = findViewById(R.id.time);
        EditText lector = findViewById(R.id.lector);
        EditText aud = findViewById(R.id.aud);

        name.setText(bundle.getString("name"));
        aud.setText(bundle.getString("aud"));
        time.setText(bundle.getString("time"));
        lector.setText(bundle.getString("lector"));


        if (JSONHelper.importFromJSON(this) == null)
            lessonList = new ArrayList<>();
        else
            lessonList = JSONHelper.importFromJSON(this);

        listAdapter = new ListAdapter(this, (ArrayList<Lesson>) lessonList);

        Lesson Lesson = (com.mtpms.lr6.Lesson) lessonList.toArray()[bundle.getInt("position")];
        lessonList.remove(Lesson);
        listAdapter.notifyDataSetChanged();

        save.setOnClickListener(v ->
        {
            Lesson.name = name.getText().toString();
            Lesson.aud = aud.getText().toString();
            Lesson.time = time.getText().toString();
            Lesson.lector = lector.getText().toString();
            lessonList.add(Lesson);
            JSONHelper.exportToJSON(this, lessonList);
            listAdapter.notifyDataSetChanged();
            Intent intentPrev = new Intent(MainActivity4.this, MainActivity.class);
            startActivity(intentPrev);
        });
    }
    public void back(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}