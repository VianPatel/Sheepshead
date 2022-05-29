package com.vian4.CardGame.generic;

import com.vian4.CardGame.deck.Card;

import java.util.ArrayList;

public abstract class CardHolder {

    protected ArrayList<Card> cardStorage = new ArrayList<>();
    //using protected so child classes can modify this value without a getter/setter
    //this is an internal field that should only be modified by child classes

    public int size() {
        return cardStorage.size();
    }
}
