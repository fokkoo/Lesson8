package com.example.newgeekbrainsapplication;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CardsSourceFirebaseImpl implements CardSource {


    public static final String COLLECTIONS = "CARDS";
    public static final String TAG = "CARDS SOURS FIRE BACE IMPL";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference collection = db.collection((COLLECTIONS));
    private List<CardData> cards = new ArrayList<>();


    @Override
    public CardSource init(CardSourceResponse response) {
        return null;
    }

    @Override
    public CardData getCardData(int position) {
        return null;
    }

    @Override
    public void deliteCardData(int position) {

    }

    @Override
    public void updateCardData(int position, CardData cardData) {

        cards.set(position, cardData);
        collection.document(cardData.getId()).set(cardData);
    }

    @Override
    public void addCardData(CardData cardData) {
        collection.document(cardData.getId()).set(cardData);

    }

    @Override
    public void clearCardData() {
       for (CardData cardData:cards){
           collection.document(cardData.getId()).delete(); // sinhronise wiht server
       }

        cards.clear();
    }

    @Override
    public int size() {
        return cards.size();
    }
}
