package com.vian4.SheepsHead.player;

import com.vian4.CardGame.deck.Card;
import com.vian4.SheepsHead.util.Utility;

public class AIPlayer extends SheepsHeadPlayer {

    @Override
    public Card selectCard(boolean leadingTurn) {
        Card playedCard = null;
        if (leadingTurn) {
            //play weakest card
            playedCard = Utility.getLowestFail(hand);
            if (playedCard == null) {
                playedCard = Utility.getLowestTrump(hand);
            }
        } else {
            if (Utility.isFail(lastPlayedCard)) {
                if (Utility.getPoints(lastPlayedCard) == 0) {
                    //fail card -> No Points

                    //  lowest fail of suit
                    playedCard = Utility.getLowestFailOfSuit(hand, lastPlayedCard.getCardSuit());
                    //  lowest any fail card
                    if (playedCard == null) {
                        playedCard = Utility.getLowestFail(hand);
                    }
                    //  lowest trump card
                    if (playedCard == null) {
                        playedCard = Utility.getLowestTrump(hand);
                    }
                } else {
                    //fail card -> Points

                    //  lowest fail claim of suit
                    playedCard = Utility.getLowestFailToClaim(hand, lastPlayedCard);
                    //  lowest fail of suit
                    if (playedCard == null) {
                        playedCard = Utility.getLowestFailOfSuit(hand, lastPlayedCard.getCardSuit());
                    }
                    //  lowest trump card
                    if (playedCard == null) {
                        playedCard = Utility.getLowestTrump(hand);
                    }
                    //  lowest fail
                    if (playedCard == null) {
                        playedCard = Utility.getLowestFail(hand);
                    }
                }
            } else {
                if (Utility.getPoints(lastPlayedCard) == 0) {
                    //trump card -> No Points

                    //  lowest trump
                    playedCard = Utility.getLowestTrump(hand);
                    //  lowest fail
                    if (playedCard == null) {
                        playedCard = Utility.getLowestFail(hand);
                    }
                } else {
                    //trump card -> Points

                    //  lowest trump claim
                    playedCard = Utility.getLowestTrumpToClaim(hand, lastPlayedCard);
                    //  lowest trump
                    if (playedCard == null) {
                        playedCard = Utility.getLowestTrump(hand);
                    }
                    //  lowest fail
                    if (playedCard == null) {
                        playedCard = Utility.getLowestFail(hand);
                    }
                }
            }
        }
        return playedCard;
    }

}
