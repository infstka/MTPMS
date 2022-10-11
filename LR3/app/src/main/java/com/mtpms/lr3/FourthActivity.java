package com.mtpms.lr3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(getResources().getDisplayMetrics().widthPixels>getResources().getDisplayMetrics().heightPixels)
        {
            setContentView(R.layout.fourth_landscape);
        }
        else
        {
            setContentView(R.layout.fourth_portrait);
        }

        //получение всех занчений
        Bundle arguments = getIntent().getExtras();
        Band band = (Band) arguments.get("band");

        SetInfo(band);
    }

    //получение значений группы
    private void SetInfo(Band band)
    {
        TextView getbandname = findViewById(R.id.getbandname);
        TextView getgenre = findViewById(R.id.getgenre);
        TextView getNOR = findViewById(R.id.getNOR);
        TextView getNOM = findViewById(R.id.getNOM);
        TextView getdate = findViewById(R.id.getdate);
        ImageView getbandlogo = findViewById(R.id.getbandlogo);

        getbandname.setText(band.BandName);
        getgenre.setText(band.Genre);
        getNOR.setText(band.NumberOfReleases);
        getNOM.setText(band.NumberOfMembers);
        getdate.setText(band.Date);

        Bitmap bmp = null;

        try
        {
            bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(band.Logo));
            getbandlogo.setImageBitmap(bmp);
        }
        catch(Exception exc)
        {
            Log.d("LR3", exc.getMessage());
        }
    }
}