package com.mtpms.lr4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        Bundle arguments = getIntent().getExtras();
        Band band = (Band) arguments.get("band");

        SetInfo(band);
    }

    private void SetInfo(Band band)
    {
        TextView getbandname = findViewById(R.id.getbandname);
        TextView getgenre = findViewById(R.id.getgenre);
        TextView getNOR = findViewById(R.id.getNOR);
        TextView getNOM = findViewById(R.id.getNOM);
        TextView getdate = findViewById(R.id.getdate);
        TextView getemail = findViewById(R.id.getemail);
        TextView getphone = findViewById(R.id.getphone);
        TextView getlink = findViewById(R.id.getlink);
        ImageView getbandlogo = findViewById(R.id.getbandlogo);

        getbandname.setText(band.BandName);
        getgenre.setText(band.Genre);
        getNOR.setText(band.NumberOfReleases);
        getNOM.setText(band.NumberOfMembers);
        getdate.setText(band.Date);
        getemail.setText(band.Email);
        getphone.setText(band.Phone);
        getlink.setText(band.Link);

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

    //звонок по нажатию на номер
    public void call(View view)
    {
        try
        {
            Intent intntCall = new Intent(Intent.ACTION_CALL);
            TextView phn = findViewById(R.id.getphone);
            String strng = phn.getText().toString();
            intntCall.setData(Uri.parse("tel:" + strng));
            startActivity(intntCall);
        }
        catch(Exception exc)
        {
            verifyPhonePermissions(this);
        }
    }

    //проверка разрешений
    public static void verifyPhonePermissions(Activity act)
    {
        int permission = ActivityCompat.checkSelfPermission(act, Manifest.permission.CALL_PHONE);
        if(permission != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(act, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
    }

    //отправка сообщения по нажатию на почту
    public void send(View view)
    {
        Intent intntMail = new Intent(Intent.ACTION_SEND);
        intntMail.setData(Uri.parse("mail to:"));
        intntMail.setType("text/plain");
        intntMail.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
        intntMail.putExtra(Intent.EXTRA_SUBJECT, "");
        intntMail.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(intntMail);
    }

    //открытие ссылки по нажатию на нее
    public void open(View view)
    {
        TextView getlink = findViewById(R.id.getlink);
        Intent intntLink = new Intent(Intent.ACTION_VIEW, Uri.parse(getlink.getText().toString()));
        intntLink.addCategory(Intent.CATEGORY_BROWSABLE);
        startActivity(intntLink);
    }
}