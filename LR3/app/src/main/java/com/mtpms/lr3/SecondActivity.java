package com.mtpms.lr3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    private String img;
    static final int GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getDisplayMetrics().widthPixels>getResources().getDisplayMetrics().heightPixels)
        {
            setContentView(R.layout.second_landscape);
        }
        else
        {
            setContentView(R.layout.second_portrait);
        }
    }

    //выбор фото из галлереи
    public void choosePhoto(View view)
    {
        Intent chsPhtInt = new Intent(Intent.ACTION_PICK);
        chsPhtInt.setType("image/*");
        startActivityForResult(chsPhtInt, GALLERY_REQUEST);
    }

   //сохранение выбранного изображения
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent)
    {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap btmp = null;
        ImageView imgvw = (ImageView) findViewById(R.id.imagelogo);
        switch(requestCode)
        {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK)
                {
                    Uri selectedImage = imageReturnedIntent.getData();
                    img = selectedImage.toString();
                    Toast.makeText(this, img, Toast.LENGTH_SHORT).show();
                    try
                    {
                        btmp = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    }
                    catch (IOException ioe)
                    {
                        ioe.printStackTrace();
                    }
                    imgvw.setImageBitmap(btmp);
                }
        }
    }
    //кнопка далее (переход к 3 активити)
    public void secNext(View view)
    {
        EditText bandName = findViewById(R.id.enterbandname);

        String bndName = bandName.getText().toString();

        Intent intent = new Intent(this, ThirdActivity.class);
        //передача значений в след. активити
        intent.putExtra("bndName", bndName);
        intent.putExtra("img", img);

        startActivity(intent);
    }

    public void secBack(View view)
    {
        this.finish();
    }
}