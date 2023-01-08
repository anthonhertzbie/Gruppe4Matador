package model;

public class Card {
    private int lineNo;
    private String cardDescription;
    private Helper helper = new Helper();




    public Card(int lineNo) {
        this.cardDescription = helper.getChancecards(lineNo);
        this.lineNo = lineNo;
    }

    public String toString() {
        StringBuilder card = new StringBuilder();
        card.append(lineNo + ". ");
        card.append(cardDescription);
        return card.toString();
    }
}
