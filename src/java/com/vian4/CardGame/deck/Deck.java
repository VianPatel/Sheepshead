package com.vian4.CardGame.deck;

import com.vian4.CardGame.deck.internal.CardSuit;
import com.vian4.CardGame.deck.internal.CardType;
import com.vian4.CardGame.generic.CardHolder;
import com.vian4.CardGame.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Deck extends CardHolder {

    public Deck() {
        this(new int[]{1,2,3,4}, new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13});
    }

    protected Deck(int[] includedSuitId, int[] includedCardId) {
        //ensures that no numbers are repeated
        Set<Integer> includedSuitIdSet = Arrays.stream(includedSuitId).boxed().collect(Collectors.toSet());
        Set<Integer> includedCardIdSet = Arrays.stream(includedCardId).boxed().collect(Collectors.toSet());
        ArrayList<Integer> includedSuitIdArr = new ArrayList<>(includedSuitIdSet);
        ArrayList<Integer> includedCardIdArr = new ArrayList<>(includedCardIdSet);

        for (int suitIdCounter = 0; suitIdCounter<includedSuitIdSet.size(); suitIdCounter++) {
            int suitId = includedSuitIdArr.get(suitIdCounter);
            for (int cardIdCounter = 0; cardIdCounter<includedCardIdSet.size(); cardIdCounter++) {
                int cardId = includedCardIdArr.get(cardIdCounter);
                cardStorage.add(new Card(new CardSuit(suitId), new CardType(cardId)));
            }
        }
    }

    public void shuffle() {
        ArrayList<Card> shuffledCards = new ArrayList<Card>(cardStorage);
        for (int i = 0; i<cardStorage.size(); i++) {
            int random = (int) (Math.random()*shuffledCards.size());
            cardStorage.set(i, shuffledCards.get(random));
            shuffledCards.remove(random);
        }
    }

    public void dealCards(Player player, int numberOfCardsToDeal) {
        for (int i = 0; i<numberOfCardsToDeal; i++) {
            player.giveCard(dealCard());
        }
    }

    public Card dealCard() {
        Card card = cardStorage.get(0);
        cardStorage.remove(0);
        return card;
    }

}
