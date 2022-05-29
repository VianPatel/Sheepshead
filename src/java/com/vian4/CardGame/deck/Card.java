package com.vian4.CardGame.deck;

import com.vian4.CardGame.deck.internal.CardSuit;
import com.vian4.CardGame.deck.internal.CardType;

public class Card {
    private CardSuit cardSuit;
    private CardType cardType;

    public Card(CardSuit cardSuit, CardType cardType) {
        this.cardSuit = cardSuit;
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }
}
