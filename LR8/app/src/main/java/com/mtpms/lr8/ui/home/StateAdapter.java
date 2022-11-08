package com.mtpms.lr8.ui.home;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mtpms.lr8.Bass;
import com.mtpms.lr8.R;

import java.util.List;


public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder>
{
    private final LayoutInflater li;
    private final List<Bass> basses;

    public StateAdapter(Context context, List<Bass> states)
    {
        this.basses = states;
        this.li = LayoutInflater.from(context);
    }

    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = li.inflate(R.layout.card_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateAdapter.ViewHolder holder, int position)
    {
        Bass bass = basses.get(position);

        holder.imgV.setImageBitmap(BitmapFactory.decodeFile(bass.getImg()));
        holder.nameV.setText(bass.getName());
        holder.stringsV.setText(bass.getStrings());
        holder.pickupsV.setText(bass.getPickups());
    }

    @Override
    public int getItemCount()
    {
        return basses.size();
    }

    //сохраняет ссылки на необходимые в элементе списка шаблоны
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        final ImageView imgV;
        final TextView nameV, stringsV, pickupsV;
        ViewHolder(View view)
        {
            super(view);
            imgV = view.findViewById(R.id.img);
            nameV = view.findViewById(R.id.name);
            stringsV = view.findViewById(R.id.strings);
            pickupsV = view.findViewById(R.id.pickups);
        }
    }
}

