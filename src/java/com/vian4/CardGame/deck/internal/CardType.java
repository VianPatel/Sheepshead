package com.vian4.CardGame.deck.internal;

import com.vian4.CardGame.deck.generic.IdAble;

public class CardType extends IdAble {

    public CardType(int id) {
        super(id);
    }

    public static int getId(String typeString) {
        switch (typeString) {
            case "a":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "j":
                return 11;
            case "q":
                return 12;
            case "k":
                return 13;
            default:
                return -1;
        }
    }

    @Override
    public String getName() {
        switch (getId()) {
            case 1:
                return "ace";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            case 10:
                return "ten";
            case 11:
                return "jack";
            case 12:
                return "queen";
            default:
                return "king";
        }
    }

    @Override
    public String getShortName() {
        switch (getId()) {
            case 1:
                return "A";
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 7:
                return "7";
            case 8:
                return "8";
            case 9:
                return "9";
            case 10:
                return "10";
            case 11:
                return "J";
            case 12:
                return "Q";
            default:
                return "K";
        }
    }

}
