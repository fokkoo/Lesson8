package com.example.newgeekbrainsapplication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CardSourceFirebaseImpl implements CardSource {

    public static final String COLLECTION = "CARDS";
    public static final String TAG = "CardSourceFirebaseImpl";

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //  открытие фаербэйс
    private CollectionReference collection = db.collection(COLLECTION); // оссинхронный список коллекции
    private List<CardData> cards = new ArrayList<>(); // подключение работы с карточками в списке телефона


    @Override
    public CardData getCardData(int position) {
        return cards.get(position); // получаем из локального хранилища
    }

    @Override
    public CardSource init(CardSourceResponse response) {
// проверка успешности получения данных
        collection.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // если запрос из интерента успешный
                        cards = new ArrayList<>(); // очищаем карточки и пресоздаем

                        for (QueryDocumentSnapshot document : task.getResult()) {

                            CardData data = document.toObject(CardData.class); // перевод из формата фаербейс в карддата
                            data.setId(document.getId()); // получение исключенного айди

                            //преобразуем документы в карточки

                            cards.add(data); //сохраняем полученные расшифрованные данные в карту

                        }
                        Log.d(TAG, "success" + cards.size());

                    } else {
                        Log.d(TAG, "failed", task.getException()); // если не получили данные то будет написано почему
                    }


                })
                .addOnFailureListener(e -> {
                    Log.d(TAG, "failed", e); // если не получили данные то будет написано почему упало
                });
        return this;
    }

    @Override
    public void deliteCardData(int position) {
        collection.document(cards.get(position).getId()).delete();
        cards.remove(position);

    }

    @Override
    public void updateCardData(int position, CardData cardData) {

        collection.document(cardData.getId()).set(cardData); // прием ПОзж объекта с полями
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
            collection.document(cardData.getId()).delete();//очищаем  и удаленную данные
        }

        cards.clear();//очищаем и локальную

    }

    @Override
    public int size() {
        return cards.size();
    }
}
