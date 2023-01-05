package org.example;

import java.sql.Array;

public class Card {
    private int lineNo;
    private String cardDescription;
    private Helper helper = new Helper();

    private Object[] card = new Object[2];


    public Object[] getCard(int lineNo) {
        card = new Object[]{lineNo, helper.getChancecards(lineNo)};
        return card;
    }
}
