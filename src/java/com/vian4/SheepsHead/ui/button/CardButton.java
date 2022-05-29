package com.vian4.SheepsHead.ui.button;

import com.vian4.CardGame.deck.Card;
import com.vian4.SheepsHead.graphics.Graphics;
import com.vian4.SheepsHead.util.Utility;

import javax.swing.*;
import java.awt.*;

public class CardButton extends JButton {
    private Card card;

    public CardButton(Card card) {
        this.card = card;

        this.setText(card.getCardSuit().getShortName()+card.getCardType().getShortName());

        //remove border padding
        this.setBorder(null);

        Font font = new Font(this.getFont().getName(), Font.BOLD, 16);
        this.setFont(font);

        if (Utility.isFail(card)) {
            this.setBackground(Color.CYAN);
        } else {
            this.setBackground(Color.GREEN);
        }

        this.setForeground(Graphics.getColor(card));
    }

    public Card getCard() {
        return card;
    }
}
