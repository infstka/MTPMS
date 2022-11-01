package com.mtpms.lr6;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import com.mtpms.lr6.databinding.FragmentContent2Binding;
import com.mtpms.lr6.databinding.FragmentContentBinding;

public class ContentFragment extends Fragment implements OnSelectedLessonListener {

    private ListAdapter adapter;
    private List<Lesson> lessons;
    FragmentContentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContentBinding.inflate(inflater, container, false);
        lessons = JSONHelper.importFromJSON(getActivity());
        if(lessons!=null){
            adapter = new ListAdapter(getContext(), (ArrayList<Lesson>) lessons);
            binding.listview.setAdapter(adapter);
            Toast.makeText(getActivity(), "Data restored", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getActivity(), "Not opened files", Toast.LENGTH_LONG).show();
        }
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                ContentFragment2 fragment2 = new ContentFragment2();
                ListView listview = binding.listview.findViewById(R.id.listview);
                registerForContextMenu(listview);
                ShowLesson(position);
            }
        });
        return binding.getRoot();
    }

    private void ShowLesson(int position) {
        Lesson lesson = (Lesson) lessons.toArray()[position];
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            Intent intent = new Intent(getActivity(), MainActivity2.class);
            intent.putExtra("name", lesson.name);
            intent.putExtra("aud", lesson.aud);
            intent.putExtra("time", lesson.time);
            intent.putExtra("lector", lesson.lector);
            startActivity(intent);
        }
    }

    FragmentContent2Binding binding2;

    @Override
    public void onLessonSelected(int position)
    {
        Lesson lesson = (Lesson) lessons.toArray()[position];


        binding2.nameLesson.setText(lesson.name);
        binding2.audLesson.setText(lesson.aud);
        binding2.timeLesson.setText(lesson.time);
        binding2.lectorLesson.setText(lesson.lector);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 0, 0, "Удалить");
        menu.add(0, 1, 0, "Изменить");
        menu.add(0, 2, 0, "Просмотреть");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case 0:
                Dialog dialogDel = new Dialog(super.getContext());
                dialogDel.setContentView(R.layout.dialog);
                TextView textViewDel = dialogDel.findViewById(R.id.dialog_info);
                textViewDel.setText("Вы хотите удалить это занятие?");
                Button yes = dialogDel.findViewById(R.id.dialog_button_yes);
                Button no = dialogDel.findViewById(R.id.dialog_button_no);
                dialogDel.show();
                yes.setOnClickListener(v->
                {
                    DeleteLesson(info.position);
                    dialogDel.cancel();
                });
                no.setOnClickListener(v -> dialogDel.cancel());
                break;
            case 1:
                Dialog dialog = new Dialog(super.getContext());
                dialog.setContentView(R.layout.dialog);
                TextView textView = dialog.findViewById(R.id.dialog_info);
                textView.setText("Вы хотите изменить это занятие?");
                Button Yes = dialog.findViewById(R.id.dialog_button_yes);
                Button No = dialog.findViewById(R.id.dialog_button_no);
                dialog.show();
                Yes.setOnClickListener(v->
                {
                    ChangeLesson(info.position);
                    dialog.cancel();
                });
                No.setOnClickListener(v -> dialog.cancel());

                break;
            case 2:
                ShowLesson(info.position);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void DeleteLesson(int position) {
        Lesson lesson = (Lesson) lessons.toArray()[position];
        lessons.remove(lesson);
        JSONHelper.exportToJSON(super.getContext(), lessons);
        adapter.notifyDataSetChanged();
    }

    private void ChangeLesson(int position) {
        Intent intent = new Intent(getActivity(), MainActivity4.class);
        Lesson lesson= (Lesson) lessons.toArray()[position];
        intent.putExtra("name", lesson.name);
        intent.putExtra("aud", lesson.aud);
        intent.putExtra("time", lesson.time);
        intent.putExtra("lector", lesson.lector);
        intent.putExtra("position", position);

        startActivity(intent);
    }

}
