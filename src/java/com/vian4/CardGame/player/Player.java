package com.vian4.CardGame.player;

import com.vian4.CardGame.deck.Card;
import com.vian4.CardGame.hand.Hand;

public abstract class Player {

    protected Hand hand = new Hand();
    //using protected so child classes can modify this value without a getter/setter
    //this is an internal field that should only be modified by child classes

    public abstract Card selectCard(boolean leadingTurn);

    public void giveCard(Card card) {
        hand.addCard(card);
    }

    public void takeCard(Card card) {
        hand.removeCard(card);
    }
}
