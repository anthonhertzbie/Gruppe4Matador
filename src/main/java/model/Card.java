package model;

public class Card {
    private int lineNo;
    private String cardDescription;
    private Helper helper = new Helper();

    private Object[] card;



    public Object[] getCard(int lineNo) {
        card = new Object[]{lineNo, helper.getChancecards(lineNo)};
        return card;
    }
}
