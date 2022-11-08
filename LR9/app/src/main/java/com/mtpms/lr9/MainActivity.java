package com.mtpms.lr9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ItemViewModel ivm;
    ItemListAdapter ila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.recyclerview);
        ila = new ItemListAdapter(new ItemListAdapter.ItemDiff());
        rv.setAdapter(ila);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ivm = new ViewModelProvider(this).get(ItemViewModel.class);
        ivm.getAllItems().observe(this, items -> { ila.submitList(items);}); //8

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewItem.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem mi)
    {
        switch (mi.getTitle().toString())
        {
            case "Удалить":
                ivm.delete(ivm.getAllItems().getValue().get(ila.getPosition()));
                break;
        }
        return super.onContextItemSelected(mi);
    }
}