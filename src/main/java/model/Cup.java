package model;

import java.util.Random;

import static model.Dice.sides;

public class Cup {
    Dice dice1 = new Dice();
    Dice dice2 = new Dice();
    private final Random RANDOM = new Random();

    /**
     * Rolls the dices and sets the faces of the die to the corresponding values
     */
    public void rollDices() {
        dice1.setFace(RANDOM.nextInt(1, sides + 1));
        dice2.setFace(RANDOM.nextInt(1, sides + 1));
    }

    /**
     * @returnthe the first die from the cup
     */
    public int getDice1() {
        return dice1.getFace();
    }

    /**
     * @return the second die from the cup
     */
    public int getDice2() {
        return dice2.getFace();

    }

    /**
     * @return the sum of the current dice throw
     */
    public int getSum() {
        int sum = getDice2() + getDice1();
        return sum;
    }
}
