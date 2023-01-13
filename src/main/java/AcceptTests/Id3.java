package AcceptTests;
import controller.*;
import model.*;
import view.*;

import java.util.ArrayList;

public class Id3 {
    private final Model model = new Model();
    private UserIO userIO;
    private final ArrayList<Notifier> notifiers = new ArrayList<>();

    private Fieldlogic_Controller fieldlogic;

    private BuyableController buyableLogic;

    public static void main(String[] args) {
        new Id3().run();
    }
    private void run(){
        Game_Controller gameController = new Game_Controller(null, model);
        View view = new View(gameController);
        gameController.addNotifier(view);
        gameController.setUserIO(new ViewUserIO(view));
        view.setGui_start();
        model.setNormalTurn(true);
        gameController.setTotalPlayerCount("1");
        view.setGuiTotalPlayers(model);
        gameController.setName(0, "Hello");

        model.getPlayerByIndex(0).setPosition(32);
        view.notifyModel(model);
        gameController.gameTurn();
    }

}
