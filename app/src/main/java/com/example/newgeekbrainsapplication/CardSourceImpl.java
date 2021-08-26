package com.example.newgeekbrainsapplication;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardSourceImpl implements CardSource {


    // private final Context context;

    private List <CardData> cards;

    public static final List <String> IMAGES = new ArrayList<>(Arrays.asList(
            "http://placekitten.com/200/300",
            "http://placekitten.com/400/300",
            "http://placekitten.com/400/400"

    )) ;




    public List<CardData> getCards() {
        return cards;
    }

    public void setCards(List<CardData> cards) {
        this.cards = cards;
    }


    @Override
    public CardSource init (CardSourceResponse response){

       //1.41.16

        return this;
    }



    public CardSourceImpl(Context context) {
        //    this.context = context;

        cards = new ArrayList<>(Arrays.asList(
                new CardData(
                        context.getResources().getString(R.string.title1),
                        context.getResources().getString(R.string.description1),
                        R.drawable.nature1,
                        false
                ),
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
                ),
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
                ),
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
                )


        )
        );
    }

    @Override
    public CardData getCardData(int position) {
        return cards.get(position);
    }

    @Override
    public void deliteCardData(int position) {
        cards.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData cardData) {
        cards.set(position, cardData);
    }

    @Override
    public void addCardData(CardData cardData) {
        cards.add(cardData);
    }

    @Override
    public void clearCardData() {
        cards.clear();
    }

    @Override
    public int size() {
        return cards.size();
    }
}
