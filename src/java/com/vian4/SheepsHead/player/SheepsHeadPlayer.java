package com.vian4.SheepsHead.player;

import com.vian4.CardGame.deck.Card;
import com.vian4.CardGame.player.Player;

public abstract class SheepsHeadPlayer extends Player {
    protected Card lastPlayedCard;
    //using protected so child classes can modify this value without a getter/setter
    //this is an internal field that should only be modified by child classes
    private boolean roundWonStatus;
    private int score;

    public int getScore() {
        return score;
    }

    public void increaseScore(int addToScore) {
        score += addToScore;
    }

    public void decreaseScore(int removeFromScore) {
        score -= removeFromScore;
    }

    public boolean getRoundWonStatus() {
        return roundWonStatus;
    }

    public void setRoundWonStatus(boolean status) {
        roundWonStatus = status;
    }

    public void showLastPlayedCard(Card card) {
        lastPlayedCard = card;
    }

}
