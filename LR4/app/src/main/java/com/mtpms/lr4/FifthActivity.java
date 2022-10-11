package com.mtpms.lr4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FifthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getDisplayMetrics().widthPixels>getResources().getDisplayMetrics().heightPixels)
        {
            setContentView(R.layout.fifth_landscape);
        }
        else
        {
            setContentView(R.layout.fifth_portrait);
        }
    }

    public void fifthNext(View view)
    {
        EditText email = findViewById(R.id.enteremail);
        EditText phone = findViewById(R.id.enterphone);
        EditText link = findViewById(R.id.enterlink);

        String sEmail = email.getText().toString();
        String sPhone = phone.getText().toString();
        String sLink = link.getText().toString();

        String bndName = getIntent().getStringExtra("bndName");
        String img = getIntent().getStringExtra("img");

        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("bndName", bndName);
        intent.putExtra("img", img);
        intent.putExtra("sEmail", sEmail);
        intent.putExtra("sPhone", sPhone);
        intent.putExtra("sLink", sLink);

        startActivity(intent);
    }

    public void fifthBack(View view)
    {
        this.finish();
    }
}