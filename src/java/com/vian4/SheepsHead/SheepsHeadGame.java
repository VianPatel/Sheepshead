package com.vian4.SheepsHead;

import com.vian4.CardGame.deck.Card;
import com.vian4.SheepsHead.deck.SheepsHeadDeck;
import com.vian4.SheepsHead.player.AIPlayer;
import com.vian4.SheepsHead.player.HumanPlayer;
import com.vian4.SheepsHead.ui.UserInterface;
import com.vian4.SheepsHead.util.Utility;

public class SheepsHeadGame {

    private SheepsHeadDeck deck;
    private AIPlayer aiPlayer;
    private HumanPlayer humanPlayer;
    
    public SheepsHeadGame() {
        deck = new SheepsHeadDeck();
        aiPlayer = new AIPlayer();
        humanPlayer = new HumanPlayer();
    }
    
    public void run() {
        //let human player lead on the first round
        humanPlayer.setRoundWonStatus(true);

        deck.shuffle();
        //using 32 card deck
        deck.dealCards(aiPlayer, 16);
        deck.dealCards(humanPlayer, 16);

        UserInterface.initFrame();
        UserInterface.welcomeMessage();

        while (aiPlayer.getScore() <= 60 && humanPlayer.getScore() <= 60) {
            playRound();
        }

        if (humanPlayer.getScore() > 60) {
            UserInterface.wonGame(humanPlayer.getScore());
        } else {
            UserInterface.lostGame(humanPlayer.getScore());
        }

    }

    private void playRound() {
        Card humanCard;
        Card aiCard;
        if (humanPlayer.getRoundWonStatus()) {
            //let human lead round
            humanCard = humanPlayer.selectCard(true);
            humanPlayer.takeCard(humanCard);
            humanPlayer.updateDisplayedHand();
            
            aiPlayer.showLastPlayedCard(humanCard);
            aiCard = aiPlayer.selectCard(false);
            aiPlayer.takeCard(aiCard);
            humanPlayer.showLastPlayedCardWithDelay(aiCard);
            //no need to print out card because round is checked for win or loss after ai plays
        } else {
            //let ai lead round
            aiCard = aiPlayer.selectCard(true);
            aiPlayer.takeCard(aiCard);
            
            humanPlayer.showLastPlayedCard(aiCard);
            humanCard = humanPlayer.selectCard(false);
            humanPlayer.takeCard(humanCard);
            humanPlayer.updateDisplayedHand();
            aiPlayer.showLastPlayedCard(humanCard);
        }

        if (Utility.cardGreaterThanCard(humanCard, aiCard)) {
            //human won
            humanPlayer.setRoundWonStatus(true);
            aiPlayer.setRoundWonStatus(false);
            int pointsWon = Utility.getPoints(humanCard)+ Utility.getPoints(aiCard);
            humanPlayer.increaseScore(pointsWon);
            UserInterface.wonRound(humanPlayer.getScore(), pointsWon, aiPlayer.getScore());
        } else {
            //human lost
            humanPlayer.setRoundWonStatus(false);
            aiPlayer.setRoundWonStatus(true);
            int aiPointsWon = Utility.getPoints(humanCard)+ Utility.getPoints(aiCard);
            aiPlayer.increaseScore(aiPointsWon);
            UserInterface.lostRound(aiPlayer.getScore(), aiPointsWon, humanPlayer.getScore());
        }

    }
}
