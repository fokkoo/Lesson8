package com.example.newgeekbrainsapplication;

import android.content.res.Resources;

import androidx.annotation.NonNull;

public class DataProvider {
    public static CardData[] getData(@NonNull Resources resources) {

        return new CardData[]{

                new CardData(
                        resources.getString(R.string.title1),
                        resources.getString(R.string.description1),
                        R.drawable.nature1,
                        false
                ),
                new CardData(

                        resources.getString(R.string.title2),
                        resources.getString(R.string.description2),
                        R.drawable.nature2,
                        false
                )
        };
    }
}
