package AcceptTests;
import controller.*;
import model.*;
import view.*;

import java.util.ArrayList;

public class Id3 {
    private final Model model = new Model();

    public static void main(String[] args) {
        new Id3().run();
    }
    private void run(){
        // setup and start
        Game_Controller gameController = new Game_Controller(null, model);
        View view = new View(gameController);
        gameController.addNotifier(view);
        gameController.setUserIO(new ViewUserIO(view));
        view.setGui_start();

        // Used parameters
        gameController.setTotalPlayerCount("2");
        view.setGuiTotalPlayers(model);
        gameController.setName(0, "Hello");
        gameController.setName(1, "no");
        model.getPlayerByIndex(0).getAccount().setBalance(100);
        model.getPlayerByIndex(1).getAccount().setBalance(40000);

        // Sets all fields to be owned by player 1
        for (int i = 0; i < 40; i++) {
            model.gameBoard().buyField(i, 1);
            view.setBorder(i, 1);
        }

        // plays the game
        model.getPlayerByIndex(0).setPosition(32);
        view.notifyModel(model);
        gameController.normalTurn();
        model.getPlayerByIndex(0).addPosition(5);
        gameController.notifierWithLogic();

    }
}
