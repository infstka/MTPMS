package com.mtpms.lr9;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView bassName, bassStrings, bassPickups;

    private ItemViewHolder(View view)
    {
        super(view);
        bassName = view.findViewById(R.id.bassName);
        bassStrings = view.findViewById(R.id.bassStrings);
        bassPickups = view.findViewById(R.id.bassPickups);

        View.OnCreateContextMenuListener occml = new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.setHeaderTitle("Выберите действие");
                contextMenu.add(0, view.getId(), 0, "Удалить");
            }
        };
        view.setOnCreateContextMenuListener(occml);
    }

    public void bind(String path, String name, String strings, String pickups)
    {
        bassName.setText(name);
        bassStrings.setText(strings);
        bassPickups.setText(pickups);
    }

    static ItemViewHolder create(ViewGroup parent)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);
        return new ItemViewHolder(view);
    }
}
