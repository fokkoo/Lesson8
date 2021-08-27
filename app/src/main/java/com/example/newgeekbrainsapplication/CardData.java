package com.example.newgeekbrainsapplication;

public class CardData {

    private String id; // id of card
    private String title;       // заголовок
    private String description; // описание
    private int picture;        // изображение
    private boolean like;       // флажок

    public CardData(String title, String description, int picture, boolean like) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.like = like;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPicture() {
        return picture;
    }

    public boolean isLike() {
        return like;
    }
}

