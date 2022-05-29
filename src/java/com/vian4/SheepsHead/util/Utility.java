package com.vian4.SheepsHead.util;

import com.vian4.CardGame.deck.Card;
import com.vian4.CardGame.deck.internal.CardSuit;
import com.vian4.CardGame.hand.Hand;

public class Utility {

	//begin card specific utility methods
	
    public static int getPoints(Card card) {
        switch (card.getCardType().getId()) {
            case 1:
                return 11;
            case 10:
                return 10;
            case 11:
                return 2;
            case 12:
                return 3;
            case 13:
                return 4;
            default:
                return 0;
        }
    }

    public static boolean isTrump(Card card) {

        //if diamond
        if (card.getCardSuit().getId() == 1) {
            return true;
        }

        //if jack or queen
        if (card.getCardType().getId() == 11 || card.getCardType().getId() == 12) {
            return true;
        }

        return false;
    }

    public static boolean isFail(Card card) {
        return !isTrump(card);
    }
    
    //end of card specific utility methods
    
    //begin ai specific utility methods
    
    public static Card getLowestFailOfSuit(Hand hand, CardSuit cardSuit) {
        Card lowestFail = null;
        for (int i = 0; i<hand.size(); i++) {
            Card selectedCard = hand.getCard(i);
            if (isFail(selectedCard) && (selectedCard.getCardSuit().getId() == cardSuit.getId())) {
                if (lowestFail == null) {
                    lowestFail = selectedCard;
                } else if (!cardGreaterThanCard(selectedCard, lowestFail)) {
                    lowestFail = selectedCard;
                }
            }
        }
        return lowestFail;
    }

    public static Card getLowestFailToClaim(Hand hand, Card targetCard) {
        Card lowestFail = null;
        for (int i = 0; i<hand.size(); i++) {
            Card selectedCard = hand.getCard(i);
            if (isFail(selectedCard) && (selectedCard.getCardSuit().getId() == targetCard.getCardSuit().getId()) && (cardGreaterThanCard(selectedCard, targetCard))) {
                if (lowestFail == null) {
                    lowestFail = selectedCard;
                } else if (!cardGreaterThanCard(selectedCard, lowestFail)) {
                    lowestFail = selectedCard;
                }
            }
        }
        return lowestFail;
    }

    public static Card getLowestFail(Hand hand) {
        Card lowestFail = null;
        for (int i = 0; i<hand.size(); i++) {
            Card selectedCard = hand.getCard(i);
            if (isFail(selectedCard)) {
                if (lowestFail == null) {
                    lowestFail = selectedCard;
                } else if (!cardGreaterThanCard(selectedCard, lowestFail)) {
                    lowestFail = selectedCard;
                }
            }
        }
        return lowestFail;
    }

    public static Card getLowestTrumpToClaim(Hand hand, Card targetCard) {
        Card lowestTrump = null;
        for (int i = 0; i<hand.size(); i++) {
            Card selectedCard = hand.getCard(i);

            if (isTrump(selectedCard) &&
                    (cardGreaterThanCard(selectedCard, targetCard) ||
                            ((selectedCard.getCardType().getId() == targetCard.getCardType().getId()) && selectedCard.getCardSuit().getId() > targetCard.getCardSuit().getId()) )) {
                if (lowestTrump == null) {
                    lowestTrump = selectedCard;
                } else if (!cardGreaterThanCard(selectedCard, lowestTrump) ||
                        ((selectedCard.getCardType().getId() == lowestTrump.getCardType().getId()) && (selectedCard.getCardSuit().getId() < lowestTrump.getCardSuit().getId())) ) {
                    lowestTrump = selectedCard;
                }
            }
        }
        return lowestTrump;
    }

    public static Card getLowestTrump(Hand hand) {
        Card lowestTrump = null;
        for (int i = 0; i<hand.size(); i++) {
            Card selectedCard = hand.getCard(i);
            if (isTrump(selectedCard)) {
                if (lowestTrump == null) {
                    lowestTrump = selectedCard;
                } else if (!cardGreaterThanCard(selectedCard, lowestTrump)) {
                    lowestTrump = selectedCard;
                }
            }
        }
        return lowestTrump;
    }
    
    //end of ai specific utility methods
    
    //begin generic utility methods

    public static boolean canPlayCard(Card card, Hand hand, Card previousPlayedCard) {
        boolean failOfSuit = hasFailOfSuit(hand, previousPlayedCard.getCardSuit());
        boolean hasTrump = hasTrump(hand);
        boolean hasFail = hasFail(hand);
        if (isFail(previousPlayedCard)) {
            //fail
            if (isFail(card)) {
                //fail fail
                if (previousPlayedCard.getCardSuit().getId() == card.getCardSuit().getId()) {
                    //suit matches
                    return true;
                }
                if (!failOfSuit) {
                    return true;
                }
                //you have fail of suit that you can play
            } else {
                //fail trump
                if (!failOfSuit) {
                    return true;
                }
                //you have fail of suit that you can play
            }

        } else {
            //trump
            if (isTrump(card)) {
                return true;
            }
            if (!hasTrump) {
                return true;
            }
        }
        return false;


    }

    private static boolean hasTrump(Hand hand) {
        boolean trump = false;
        for (int i = 0; i<hand.size(); i++) {
            Card selectedCard = hand.getCard(i);
            if (isTrump(selectedCard)) {
                trump = true;
            }
        }
        return trump;
    }

    private static boolean hasFail(Hand hand) {
        boolean fail = false;
        for (int i = 0; i<hand.size(); i++) {
            Card selectedCard = hand.getCard(i);
            if (isFail(selectedCard)) {
                fail = true;
            }
        }
        return fail;
    }

    private static boolean hasFailOfSuit(Hand hand, CardSuit suit) {
        boolean failOfSuit = false;
        for (int i = 0; i<hand.size(); i++) {
            Card selectedCard = hand.getCard(i);
            if (isFail(selectedCard) && (selectedCard.getCardSuit().getId() == suit.getId())) {
                failOfSuit = true;
            }
        }
        return failOfSuit;
    }

    //Note: this method checks if the first card is greater than the second one
    public static boolean cardGreaterThanCard(Card card1, Card card2) {
        if (isFail(card1)) {
            if (isFail(card2)) {
                //fail fail


                //reorder ids
                int card1Type = card1.getCardType().getId();
                int card2Type = card2.getCardType().getId();
                //change ace id to 10, kings to 8 and shift cards below 10 by two, shift 10 to 9
                if (card1Type == 1) {
                	card1Type = 10;
                } else if (card1Type == 13) {
                    card1Type = 8;
                } else if (card1Type == 10) {
                    card1Type = 9;
                } else if (card1Type < 10) {
                        card1Type -= 2;
                }
                if (card2Type == 1) {
                    card2Type = 10;
                } else if (card2Type == 13) {
                    card2Type = 8;
                } else if (card2Type == 10) {
                    card2Type = 9;
                } else if (card2Type < 10) {
                    card2Type -= 2;
                }
                
                if (card1.getCardSuit() == card2.getCardSuit() || card1Type != card2Type) {
                	//same suit of it cards are different values
                	return (card1Type > card2Type);
                } else {
                	//smaller suit values are greater
                	return (card1Type < card2Type);
                }
            } else {
                //fail trump
                return false;
            }
        } else {
            if (isFail(card2)) {
                //trump fail
                return true;
            } else {
                //trump trump

                //greater number

                //correct ids
                int card1Type = card1.getCardType().getId();
                int card2Type = card2.getCardType().getId();
                //change ace id to 10, kings to 8 and shift cards below 10 by two, shift 10 to 9
                if (card1Type == 1) {
                    card1Type = 10;
                } else if (card1Type == 13) {
                    card1Type = 8;
                } else if (card1Type == 10) {
                    card1Type = 9;
                } else if (card1Type < 10) {
                    card1Type -= 2;
                }
                if (card2Type == 1) {
                    card2Type = 10;
                } else if (card2Type == 13) {
                    card2Type = 8;
                } else if (card2Type == 10) {
                    card2Type = 9;
                } else if (card2Type < 10) {
                    card2Type -= 2;
                }

                if (card1Type > card2Type) {
                    //greater number
                    return true;
                }


                if ((card1.getCardType().getId() == card2.getCardType().getId()) && (card1.getCardSuit().getId() > card2.getCardSuit().getId())) {
                    //same number and greater suit
                    return true;
                } else {
                    return false;
                }
            }
        }
    }


    public static void sleep(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (Exception ignored) {}
    }
    
  //end of generic utility methods

}
