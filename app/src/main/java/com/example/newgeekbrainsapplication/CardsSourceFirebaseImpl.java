package com.example.newgeekbrainsapplication;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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

        collection.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            cards = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                      

                            }
                        }
                    }
                });

        return this;
    }

    @Override
    public CardData getCardData(int position) {
        return cards.get(position);
    }

    @Override
    public void deliteCardData(int position) {
        collection.document(cards.get(position).getId()).delete();
        cards.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData cardData) {


        collection.document(cardData.getId()).set(cardData);
        cards.set(position, cardData);
    }

    @Override
    public void addCardData(CardData cardData) {
        collection.document(cardData.getId()).set(cardData);
        cards.add(cardData);
    }

    @Override
    public void clearCardData() {
        for (CardData cardData : cards) {
            collection.document(cardData.getId()).delete(); // sinhronise wiht server
        }

        cards.clear();
    }

    @Override
    public int size() {
        return cards.size();
    }
}
