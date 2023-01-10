package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


    @Test
    @DisplayName("a name set is getable")
    void nameSetGet(){
        Player player = new Player();
        player.setName("Kate");
        assertEquals("Kate", player.getName());
    }

    @Test
    @DisplayName("a position can not be > 39")
    void positionLimit(){
        Player player = new Player();
        player.setPosition(39);
        player.addPosition(1);
    }

    @Test
    @DisplayName("a player can be in jail")
    void inJail(){
        Player player = new Player();
        player.setInJail(true);
        assertEquals(true, player.getInJail());
    }

    @Test
    @DisplayName("Player position is moved the number of eyes on the dices")
    void playerPositionIsCorrect(){
        Player player = new Player();
        player.setPosition(0);

    }

    //ask about this
    @Test
    void thatEverythingWork() {
        Player player = new Player();

    }


}
