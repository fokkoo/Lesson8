package com.example.newgeekbrainsapplication;

public interface CardSource {

    CardData getCardData(int position);



    void deliteCardData (int position);
    void updateCardData (int position, CardData cardData);
    void addCardData ( CardData cardData);
    void clearCardData ();


    int size();
}
