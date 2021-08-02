package com.example.newgeekbrainsapplication;


import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ItemViewHolder> {

    public static final int VIEW_TYPE_IMAGE = 1;
    public static final int VIEW_TYPE_TEXT = 0;
    private final String[] dataSource;
    public static final String TAG = "item Adapter";
    private AdapterView.OnItemClickListener listener;


    public itemAdapter(String[] dataSource) {
        Log.d(TAG, "item Adapter" + Arrays.toString(dataSource));

        this.dataSource = dataSource;
    }


    public void setListener(@Nullable AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        Log.d(TAG, "onCreateViewHolder"+viewType);
        View view;

        if ( viewType == VIEW_TYPE_IMAGE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        }


        return new ItemViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position % 4 == 0? VIEW_TYPE_IMAGE :VIEW_TYPE_TEXT;  // если позиция 4 то вставляется константа для картинки в остальных случаях текст
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder" + position);


        if (getItemViewType(position)== VIEW_TYPE_IMAGE){
holder.getImageView().setImageResource(R.drawable.promotion);
        }else{
            holder.getTextView().setText(dataSource[position]);
        }

/*
        // прописывания условия при котором должно быть красным поле. остальные поля надо принудительно делать не красными.
        if (position % 5 == 0) {
            holder.getTextView().setBackgroundColor(Color.RED);
        }
        else{
            holder.getTextView().setBackgroundColor(Color.WHITE);
        }
*/


    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textView.setOnClickListener(v -> {
                listener.onItemClick(getAdapterPosition()); // todo this tomorrow
            });



            imageView = itemView.findViewById(R.id.image);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getImageView() {
            return imageView;
        }
    }

    interface onItemClickListner {
        void onItemClick(int position); // which position is clicked
    }

}
