package com.vian4.SheepsHead.ui;

import com.vian4.CardGame.deck.Card;
import com.vian4.CardGame.hand.Hand;
import com.vian4.SheepsHead.graphics.Graphics;
import com.vian4.SheepsHead.util.Utility;

import javax.swing.*;

public class UserInterface {

    private static GameFrame gameFrame;

    public static void initFrame() {
        gameFrame = new GameFrame();
    }

    public static void welcomeMessage() {
        JOptionPane.showMessageDialog(gameFrame, "Welcome to the sheepshead card game!\n" +
                "\n" +
                "Sheepshead is a card game that plays with a 32 card deck.\n" +
                "It uses the Ace, Seven, Eight, Nine, Ten, Jack, King, and Queen from a standard 52 card deck.\n" +
                "You will play against an AI and will both be dealt 16 cards.\n" +
                "Your objective is to reach 61 points before the AI.\n"
                , "Game Info", JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(gameFrame, "There are two types of cards: fail and trump.\n" +
                "(This game has no relation to modern day politics)\n" +
                "\n" +
                "If you are leading the turn, you are free to place any card of your choosing.\n" +
                "If you are following the turn (going after another player) then you must place a card of the same suit if the card played is fail\n"+
                "If the card played is trump, you must also play a trump card\n" +
                "If you do not have a card that satisfies these constraints then you are free to play any card of your choosing\n" +
                "Note: suit is irrelevant for trump cards, it only determines which trump card is greater"
               
                , "Game Info", JOptionPane.INFORMATION_MESSAGE);

        ImageIcon cardRanking = new ImageIcon(UserInterface.class.getResource("/cardRanking.png"));
        JOptionPane.showMessageDialog(gameFrame, "Here are the card rankings for trump cards\n" +
                "Image source: https://www.sheepshead.org/rules/sheepshead-basic-rules/"
                , "Game Info", JOptionPane.INFORMATION_MESSAGE, cardRanking);

        ImageIcon failCards = new ImageIcon(UserInterface.class.getResource("/failCards.png"));
        JOptionPane.showMessageDialog(gameFrame, "Here are the card rankings for fail cards (please note that all diamonds are trump cards).\n" +
                "Image source: https://www.sheepshead.org/rules/sheepshead-basic-rules/"
                , "Game Info", JOptionPane.INFORMATION_MESSAGE, failCards);

        ImageIcon cardPoints = new ImageIcon(UserInterface.class.getResource("/cardPoints.png"));
        JOptionPane.showMessageDialog(gameFrame, "Here are the points for the game cards\n" +
                        "Image source: https://www.sheepshead.org/rules/sheepshead-basic-rules/"
                , "Game Info", JOptionPane.INFORMATION_MESSAGE, cardPoints);

        JOptionPane.showMessageDialog(gameFrame, "Please note that the blue cards in your hand are fail cards and\n" +
                        "the green cards are trump."
                , "Game Info", JOptionPane.INFORMATION_MESSAGE);

        //display frame
        gameFrame.setVisible(true);
    }

    public static Card selectCard(Hand hand, Card previousPlay, boolean leadingRound) {
        if (leadingRound) {
            gameFrame.getGameMessage().setText("You are leading this turn. You are free to choose any card.");
        }

        while (true) {
            Card selectedCard = selectCard();
            if (leadingRound || Utility.canPlayCard(selectedCard, hand, previousPlay)) {
                return selectedCard;
            }
        }
    }

    public static void updateHand(Hand hand) {
        gameFrame.displayHand(hand);
    }

    private static Card selectCard() {
        if (gameFrame.getSelectedCard() == null) {
            try {
                Thread.sleep(100);
            } catch (Exception ignored) {}
            return selectCard();
        } else {
            Card selectedCard = gameFrame.getSelectedCard();
            gameFrame.clearSelectedCard();
            return selectedCard;
        }
    }

    public static void aiPlayed(Card card) {
        String cardType;
        if (Utility.isFail(card)) {
            cardType = "(fail)";
        } else {
            cardType = "(trump)";
        }

        String cardText;
        if (Graphics.isCardRed(card)) {
            cardText = "<p style=\"color:rgba(255,0,0,0.5);\">"+card.getCardSuit().getShortName()+card.getCardType().getShortName()+" "+cardType+"<p>";
        } else {
            cardText = "<p>"+card.getCardSuit().getShortName()+card.getCardType().getShortName()+" "+cardType+"<p>";
        }
        gameFrame.getGameMessage().setRawText("<html><p>The ai played a: </p>"+cardText+"worth "+ Utility.getPoints(card)+" points</html>");
    }

    public static void wonRound(int newHumanScore, int humanpointsWon, int aiScore) {
        gameFrame.getHumanPointsText().setText("Points: "+ newHumanScore);
        gameFrame.getGameMessage().setText("You won this round and got "+humanpointsWon+" points!");
        Utility.sleep(3.5);
    }

    public static void lostRound(int newAiScore, int aiPointsWon, int humanScore) {
        gameFrame.getAiPointsText().setText("Points: " + newAiScore);
        gameFrame.getGameMessage().setText("You lost this round and the AI got "+aiPointsWon+" points!");
        Utility.sleep(3.5);
    }

    public static void wonGame(int points) {
        gameFrame.clearHand(true);
        gameFrame.getGameMessage().setText("Congratulations on winning the game! You won with a score of "+points+" points!");
    }

    public static void lostGame(int points) {
        gameFrame.clearHand(true);
        gameFrame.getGameMessage().setText("You lost the game. You lost with a score of "+points+" points.");
    }

}
