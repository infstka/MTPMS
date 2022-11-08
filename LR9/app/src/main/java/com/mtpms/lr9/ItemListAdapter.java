package com.mtpms.lr9;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ItemListAdapter extends ListAdapter<BassItem, ItemViewHolder> {

    private int position;
    public ItemListAdapter(@NonNull DiffUtil.ItemCallback<BassItem> diffCallback)
    {
        super(diffCallback);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return ItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        BassItem bi = getItem(position);
        holder.bind(bi.img, bi.name, bi.strings, bi.strings);
        holder.itemView.setOnLongClickListener(view ->
        {
            setPosition(position);
            return false;
        });
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    static class ItemDiff extends DiffUtil.ItemCallback<BassItem>
    {
        @Override
        public boolean areItemsTheSame(@NonNull BassItem oldItem, @NonNull BassItem newItem)
        {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull BassItem oldItem, @NonNull BassItem newItem)
        {
            return oldItem.getId() == newItem.getId();
        }
    }
}
