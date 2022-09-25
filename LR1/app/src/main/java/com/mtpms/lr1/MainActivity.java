package com.mtpms.lr1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//TODO Add new method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int count = 0; count < 10; count++) {
           Log.d("MainActivity", " counter =" + count);
        }
        LR1Method();
    }

    private void LR1Method() {
        LR1Class lr1Class = new LR1Class();
        TextView tv = findViewById(R.id.newtest);
        tv.setText(lr1Class.getValue());
    }
}