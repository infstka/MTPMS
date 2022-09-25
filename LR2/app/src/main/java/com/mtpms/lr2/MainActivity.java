package com.mtpms.lr2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView result;
    public Button add;
    public EditText sumnum, procnum, daysnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        add = findViewById(R.id.add);
        sumnum = findViewById(R.id.sumnum);
        procnum = findViewById(R.id.procnum);
        daysnum = findViewById(R.id.daysnum);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double summa = Double.parseDouble(sumnum.getText().toString());
                double percents = Double.parseDouble(procnum.getText().toString());
                int days = Integer.parseInt(daysnum.getText().toString());
                double res = (summa * percents / 365 * days) / 100;
                result.setText(String.valueOf(res));
            }
        });
    }
}