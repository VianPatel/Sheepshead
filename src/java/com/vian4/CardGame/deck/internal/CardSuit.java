package com.vian4.CardGame.deck.internal;

import com.vian4.CardGame.deck.generic.IdAble;

public class CardSuit extends IdAble {

    public CardSuit(int id) {
        super(id);
    }

    public static int getId(char suitChar) {
        switch (suitChar) {
            case 'd':
                return 1;
            case 'h':
                return 2;
            case 's':
                return 3;
            case 'c':
                return 4;
            default:
                return -1;
        }
    }

    @Override
    public String getName() {
        switch (getId()) {
            case 1:
                return "diamond";
            case 2:
                return "heart";
            case 3:
                return "spade";
            default:
                return "club";
        }
    }

    @Override
    public String getShortName() {
        switch (getId()) {
            case 1:
                return "♦";
            case 2:
                return "♥";
            case 3:
                return "♠";
            default:
                return "♣";
        }
    }

}
