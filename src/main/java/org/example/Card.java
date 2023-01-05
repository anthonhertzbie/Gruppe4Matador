package org.example;

import java.sql.Array;

public class Card {
    private Object[] card = new String[2];
    Helper helper = new Helper();


    public Object[] getCard(int lineNo) {
        card = new Object[]{lineNo, helper.getChancecards(lineNo)};
        System.out.println(card[0]);
        System.out.println(card[1]);
        return card;
    }
}
