package com.mtpms.lr6;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter {
    public ListAdapter(Context context, ArrayList<Lesson> recipes)
    {
        super(context, R.layout.list_item, recipes);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Lesson lesson = (Lesson) getItem(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from((getContext())).inflate(R.layout.list_item, parent, false);
        }

        TextView lessonName = convertView.findViewById(R.id.lessonName);
        TextView lessonAud = convertView.findViewById(R.id.lessonAud);
        TextView lessonTime = convertView.findViewById(R.id.lessonTime);

        lessonName.setText(lesson.name);
        lessonAud.setText(lesson.aud);
        lessonTime.setText(lesson.time);

        return convertView;
    }}
