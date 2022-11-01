package com.mtpms.lr6;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mtpms.lr6.databinding.FragmentContent2Binding;

interface OnSelectedLessonListener
{
    void onLessonSelected(int position);
}


public class ContentFragment2 extends Fragment {

    FragmentContent2Binding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void render(LayoutInflater inflater) {
        binding =  FragmentContent2Binding.inflate(inflater);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentContent2Binding.inflate(inflater);

        Intent intent = getActivity().getIntent();
        if (intent != null)
        {
            String name = intent.getStringExtra("name");
            String aud = intent.getStringExtra("aud");
            String time = intent.getStringExtra("time");
            String lector = intent.getStringExtra("lector");
            String lesson = intent.getStringExtra("lesson");

            binding.nameLesson.setText(name);
            binding.audLesson.setText(aud);
            binding.timeLesson.setText(time);
            binding.lectorLesson.setText(lector);
        }
        return binding.getRoot();
    }

    public void setSelectedItemV(Intent intent)
    {



    }


}
