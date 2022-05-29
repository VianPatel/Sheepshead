package com.vian4.SheepsHead.player;

import com.vian4.CardGame.deck.Card;
import com.vian4.SheepsHead.ui.UserInterface;
import com.vian4.SheepsHead.util.Utility;

public class HumanPlayer extends SheepsHeadPlayer {

	
    public void showLastPlayedCardWithDelay(Card card) {
        this.showLastPlayedCard(card);
        Utility.sleep(3);
    }
    
    @Override
    public void showLastPlayedCard(Card card) {
        super.showLastPlayedCard(card);
        UserInterface.aiPlayed(card);
    }

    @Override
    public Card selectCard(boolean leadingTurn) {
        updateDisplayedHand();
        return UserInterface.selectCard(hand, lastPlayedCard, leadingTurn);
    }

    public void updateDisplayedHand() {
        UserInterface.updateHand(hand);
    }

}
