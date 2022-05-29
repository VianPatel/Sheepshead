package com.vian4.SheepsHead.ui;

import com.vian4.CardGame.deck.Card;
import com.vian4.CardGame.hand.Hand;
import com.vian4.SheepsHead.ui.button.CardButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame implements ActionListener {

    private Card selectedCard;
    private ChangeableText aiPointsText;
    private ChangeableText humanPointsText;
    private ChangeableText gameMessage;
    private ArrayList<CardButton> buttons = new ArrayList<>();

    public GameFrame() {
        super("Sheepshead Game");
        this.setLayout(null);
        this.setSize(750, 650);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel humanPointsTitle = new JLabel("Human Points:");
        humanPointsTitle.setBounds(5, 15, 100, 10);
        humanPointsTitle.setVisible(true);
        this.add(humanPointsTitle);
        humanPointsText = new ChangeableText(this, 5, 25);
        humanPointsText.setText("Points: 0");

        JLabel aiPointsTitle = new JLabel("AI Points:");
        aiPointsTitle.setBounds(380, 15, 100, 10);
        this.add(aiPointsTitle);
        aiPointsText = new ChangeableText(this, 380, 25);
        aiPointsText.setText("Points: 0");

        gameMessage = new ChangeableText(this, 30, 300, 345, 60);

        this.setVisible(true);
    }

    public ChangeableText getAiPointsText() {
        return aiPointsText;
    }

    public ChangeableText getHumanPointsText() {
        return humanPointsText;
    }

    public ChangeableText getGameMessage() {
        return gameMessage;
    }

    public void displayHand(Hand hand) {
        clearHand(false);
        for (int i = 0; i<hand.size(); i++) {
            Card selectedCard = hand.getCard(i);
            CardButton cardButton = new CardButton(selectedCard);
            this.add(cardButton, i);
            buttons.add(cardButton);
            cardButton.addActionListener(this);

            int rowFromBottom = i/12;
            int height = (this.getHeight()-110)-(rowFromBottom*60);
            int width = 10+60*(i-rowFromBottom*12);

            cardButton.setBounds(width, height, 50, 50);

            this.repaint();
        }

    }

    public void clearHand(boolean repaintFrame) {
        for (int i = 0; i<buttons.size(); i++) {
            this.remove(buttons.get(i));
        }
        buttons.clear();

        if (repaintFrame) {
            this.repaint();
        }
    }

    public void clearSelectedCard() {
        this.selectedCard = null;
    }

    public Card getSelectedCard() {
        return this.selectedCard;
    }

    //button clicked event
    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (!(source instanceof CardButton) || selectedCard != null) {
            return;
        }
        selectedCard = ((CardButton) source).getCard();
    }
}
