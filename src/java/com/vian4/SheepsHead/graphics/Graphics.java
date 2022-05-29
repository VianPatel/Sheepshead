package com.vian4.SheepsHead.graphics;

import com.vian4.CardGame.deck.Card;

import java.awt.*;

public class Graphics {

    public static Color getColor(Card card) {
        if (card.getCardSuit().getId() == 1 || card.getCardSuit().getId() == 2) {
            return Color.RED;
        } else {
            return Color.BLACK;
        }
    }

    public static boolean isCardRed(Card card) {
        return (card.getCardSuit().getId() == 1 || card.getCardSuit().getId() == 2);
    }
}
