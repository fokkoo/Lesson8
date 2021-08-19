package com.example.newgeekbrainsapplication;


import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ItemViewHolder> {

    //    public static final int VIEW_TYPE_IMAGE = 1;
    //   public static final int VIEW_TYPE_TEXT = 0;
   // public static final int NO_POSITION = -1;
  //  private int currentPosition = NO_POSITION; // позиция элемента для его последующего удаления
    //
    private final CardSource dataSource;

    private OnItemClickListener listener;
    public static final String TAG = "ItemAdapter";


    public itemAdapter(CardSource dataSource) {
        this.dataSource = dataSource;
        Log.d(TAG, "ItemAdapter");
    }


    public void setListener(@Nullable OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        Log.d(TAG, "onCreateViewHolder" + viewType);
        View view;
/*
        if (viewType == VIEW_TYPE_IMAGE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        }
*/
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    /*
        @Override
        public int getItemViewType(int position) {
            return position % 4 == 0 ? VIEW_TYPE_IMAGE : VIEW_TYPE_TEXT;  // если позиция 4 то вставляется константа для картинки в остальных случаях текст
        }
    */
    @Override
    public void onBindViewHolder(@NonNull itemAdapter.ItemViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder" + position);

        holder.bind(dataSource.getCardData(position));

        /*
        if (getItemViewType(position) == VIEW_TYPE_IMAGE) {
           holder.getImageView().setImageResource(R.drawable.promotion);
        } else {
            holder.getTextView().setText(dataSource[position]);
        }
        */

        //    holder.getTextView().setText(dataSource[position]);
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
        return dataSource.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;
        private final ImageView imageView;
        private final CheckBox like;
        //  private final ImageView imageView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.imageView);
            like = itemView.findViewById(R.id.like);
        }

        public void bind(CardData cardData) {
            title.setText(cardData.getTitle());
            description.setText(cardData.getDescription());
            imageView.setImageResource(cardData.getPicture());
            like.setChecked(cardData.isLike());


            imageView.setOnClickListener(v -> listener.onItemClick(imageView, getLayoutPosition()));
        }
        //   imageView = itemView.findViewById(R.id.image);
    }

// 1:03

    interface OnItemClickListener {
        void onItemClick( View view, int position); // which position is clicked
    }
    //   public TextView getTextView() {
    //       return textView;
    //  }



       /* public ImageView getImageView() {
            return imageView;
        }*/


}




