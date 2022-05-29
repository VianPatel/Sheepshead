package com.vian4.CardGame.hand;

import com.vian4.CardGame.deck.Card;
import com.vian4.CardGame.generic.CardHolder;

import java.util.ArrayList;

public class Hand extends CardHolder {

    public void addCard(Card card) {
        cardStorage.add(card);
    }

    public void removeCard(Card card) {
        cardStorage.remove(card);
    }

    public ArrayList<Card> getCards() {
        return cardStorage;
    }

    public Card getCard(int index) {
        return cardStorage.get(index);
    }

}
