package com.mtpms.lr8.ui.gallery;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mtpms.lr8.Bass;
import com.mtpms.lr8.databinding.FragmentGalleryBinding;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    private EditText enterName;
    private EditText enterStrings;
    private EditText enterPickups;
    private String Img;
    private Button loadI;
    private Button saveI;

    private List<Bass> basses;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSION_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity)
    {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(activity, PERMISSION_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        enterName = binding.enterName;
        enterStrings = binding.enterStrings;
        enterPickups = binding.enterPickups;

        loadI = binding.loadI;
        loadI.setOnClickListener(loadImg);

        saveI = binding.saveI;
        saveI.setOnClickListener(saveImg);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private View.OnClickListener loadImg = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try
            {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
            catch (Exception exc)
            {
                verifyStoragePermissions(getActivity());
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null)
        {
            switch (requestCode)
            {
                case 1:
                    Uri selectedImg = data.getData();
                    String[] fpc = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContext().getContentResolver().query(selectedImg, fpc, null, null, null);
                    ((Cursor) cursor).moveToFirst();
                    int colIndex;
                    colIndex = cursor.getColumnIndex(fpc[0]);
                    String pictPath = cursor.getString(colIndex);
                    Img = pictPath;
                    cursor.close();
                    Toast tst = Toast.makeText(getContext(), Img, Toast.LENGTH_SHORT);
                    tst.show();
                    break;
            }
        }
    }

    private View.OnClickListener saveImg = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try
            {
                String name = enterName.getText().toString();
                String strings = enterStrings.getText().toString();
                String pickups = enterPickups.getText().toString();

                Bass bass = new Bass(name, strings, pickups, Img);
                basses = JSON.importFromJSON(getContext());
                if(basses == null)
                {
                    basses = new ArrayList<>();
                }
                basses.add(bass);
                JSON.exportToJSON(getContext(), basses);
            }
            catch (Exception exc)
            {

            }
        }
    };
}