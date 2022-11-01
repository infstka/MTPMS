package com.mtpms.lr6;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private List<Lesson> lessonList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        if (JSON.importFromJSON(this) == null)
            lessonList = new ArrayList<>();
        else
            lessonList = JSON.importFromJSON(this);

        Button save = findViewById(R.id.save);
        EditText name = findViewById(R.id.name);
        EditText time = findViewById(R.id.time);
        EditText lector = findViewById(R.id.lector);
        EditText aud = findViewById(R.id.aud);

        save.setOnClickListener(v ->
        {
            if (!name.getText().toString().equals("") || !aud.getText().toString().equals("") || !lector.getText().toString().equals("") ||
                    !time.getText().toString().equals(""))
            {

                Lesson Lesson = new Lesson(name.getText().toString(), aud.getText().toString(), lector.getText().toString(), time.getText().toString());
                lessonList.add(Lesson);

                JSON.exportToJSON(this, lessonList);

                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast toast = Toast.makeText(this, "Проверьте правильность введённых данных", Toast.LENGTH_LONG);
            }
        });
    }
    /**addToBackStack**/
    public void back(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}