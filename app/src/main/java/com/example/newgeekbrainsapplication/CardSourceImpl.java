package com.example.newgeekbrainsapplication;

import android.content.Context;

public class CardSourceImpl implements CardSource {


    private final Context context;

    private CardData[] cards;


    public CardSourceImpl(Context context) {
        this.context = context;

        cards = new CardData[]{
                new CardData(
                        context.getResources().getString(R.string.title1),
                        context.getResources().getString(R.string.description1),
                        R.drawable.benchpress,
                        false
                ),
                new CardData(
                        context.getResources().getString(R.string.title2),
                        context.getResources().getString(R.string.description2),
                        R.drawable.deadlift,
                        false
                ),
                new CardData(
                        context.getResources().getString(R.string.title2),
                        context.getResources().getString(R.string.description2),
                        R.drawable.nature1,
                        false
                )
        };


    }

    @Override
    public CardData getCardData(int position) {
        return cards[position];
    }

    @Override
    public int size() {
        return cards.length;
    }
}
