package org.example;

import java.util.Random;

public class Deck {
    private Card[] cards = new Card[45];

    public void createDeck() {

    }
    public Card getFirstCard(){
        return cards[0];
    }

    public Card getLastCard(){
        return cards[45];
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int a = random.nextInt(0, 45);
            int b = random.nextInt(0, 45);
            Card savedcard = cards[a];
            cards[a] = cards[b];
            cards[b] = savedcard;
        }
    }
}
