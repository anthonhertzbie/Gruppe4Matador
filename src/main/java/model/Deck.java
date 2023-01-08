package model;

import java.util.Random;

public class Deck {

    private Card[] deck = new Card[45];
    private Helper helper = new Helper();



    public void createDeck() {
        for(int i = 0; i<45; i++){
            deck[i] = new Card(i);
        }
    }

    public Card getFirstCard(){
        return deck[0];
    }

    public Card getLastCard(){
        return deck[45];
    }
    public Card drawCard(){
        Card lastCard = getFirstCard();
        for (int i = 0; i < deck.length; i++) {
            deck[i] = deck[i+1];
        }
        deck[deck.length - 1] = lastCard;
        return lastCard;
    }

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
