package model;

import java.util.Random;

public class Deck {

    private Card[] deck = new Card[45];



    public Deck() {
        for(int i = 0; i<45; i++){
            deck[i] = new Card(i);
        }
        shuffle();
    }

    /**
     * @return the card that's on top of the deck
     */
    public Card getFirstCard(){
        return deck[0];
    }

    public void setFirstCard(int firstCard){
        Card savedcard = deck[0];
        for (Card card : deck) {
            if (firstCard == card.getCardNumber()) {
                savedcard = card;
            }
        }
        deck[0] = savedcard;
    }
    /**
     * @return the card in the bottom of the deck
     */
    public Card getLastCard(){
        return deck[44];
    }

    /**
     * the takes the first card of the deck returns it and puts it last
     * @return the first card
     */
    public Card drawCard(){
        Card firstCard = getFirstCard();

        for (int i = 0; i < deck.length - 1; i++) {
            deck[i] = deck[i+1];
        }
        deck[deck.length - 1] = firstCard;

        return firstCard;
    }

    /**
     * Shuffles the cards by taking two random cards and switching them 10000 times.
     */
    public void shuffle() {

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int a = random.nextInt(0, 45);
            int b = random.nextInt(0, 45);
            Card savedcard = deck[a];
            deck[a] = deck[b];
            deck[b] = savedcard;
        }
    }



}
