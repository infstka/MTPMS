package com.mtpms.lr9;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class NewItem extends AppCompatActivity {

    private EditText enterName;
    private EditText enterStrings;
    private EditText enterPickups;

    private ItemViewModel ivm;
    ItemListAdapter isa;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);
        enterName = findViewById(R.id.enterName);
        enterStrings = findViewById(R.id.enterStrings);
        enterPickups = findViewById(R.id.enterPickups);

        final Button button = findViewById(R.id.save);

        ivm = new ViewModelProvider(this).get(ItemViewModel.class);
        isa = new ItemListAdapter(new ItemListAdapter.ItemDiff());
        ivm.getAllItems().observe(this, items -> {isa.submitList(items);});

        button.setOnClickListener(view -> {
            if(enterName.getText().toString().equals("") || enterStrings.getText().toString().equals("") || enterPickups.getText().toString().equals(""))
            {
                Toast tst = Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG);
                tst.show();
            }
            else
            {
                ivm.insert(new BassItem(null, enterName.getText().toString(), enterStrings.getText().toString(), enterPickups.getText().toString()));
            }
            finish();
        });
    }
}
