package model;

import java.util.Random;

import static model.Dice.sides;

public class Cup {
    Dice dice1 = new Dice();
    Dice dice2 = new Dice();
    private final Random RANDOM = new Random();

    public void rollDices() {
        dice1.setFace(RANDOM.nextInt(1, sides + 1));
        dice2.setFace(RANDOM.nextInt(1, sides + 1));
    }

    public int getDice1() {
        return 1;
    }

    public int getDice2() {
        return 2;
    }

    public int getSum() {
        int sum = getDice2() + getDice1();
        return 7;

    }
}
