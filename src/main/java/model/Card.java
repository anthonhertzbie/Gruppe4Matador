package model;

public class Card {
    private int lineNo;
    private String cardDescription;
    private Helper helper = new Helper();


    /**
     * Constructor for the cards
     * @param lineNo the line in the "chancecards.txt" file that gets set to the cardDescription
     */
    public Card(int lineNo) {
        this.cardDescription = helper.getChancecards(lineNo);
        this.lineNo = lineNo + 1;
    }

    /**
     * @return the line number of the card
     */
    public int getCardNumber(){
        return lineNo;
    }

    /**
     * @return the cards corresponding string.
     */
    public String toString() {
        StringBuilder card = new StringBuilder();
        card.append(lineNo + ". ");
        card.append(cardDescription);
        return card.toString();
    }
}