package com.example.newgeekbrainsapplication;

public interface CardSource {

    CardData getCardData(int position);

    int size();

}
