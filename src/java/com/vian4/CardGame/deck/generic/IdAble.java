package com.vian4.CardGame.deck.generic;

public abstract class IdAble {
    private int id;

    public IdAble(int id) {
        this.id = id;
    }

    public abstract String getName();

    public abstract String getShortName();

    public int getId() {
        return id;
    }
}
