package AcceptTests;
import controller.*;
import model.*;
import view.*;

import java.util.ArrayList;

public class Id3 {
    private final Model model = new Model();
    private final Account account = new Account();
    private UserIO userIO;
    private final ArrayList<Notifier> notifiers = new ArrayList<>();

    private Fieldlogic_Controller fieldlogic;

    private BuyableController buyableLogic;

    public static void main(String[] args) {
        new Id3().run();
    }
    void run(){
        Game_Controller gameController = new Game_Controller(null, model);
        View view = new View(gameController);
        gameController.addNotifier(view);
        this.userIO = new ViewUserIO(view);
        gameController.setUserIO(userIO);
        view.setGui_start();
        model.setNormalTurn(true);
        gameController.setTotalPlayerCount("2");
        view.setGuiTotalPlayers(model);
        gameController.setName(0, "Tom");
        gameController.setName(1, "Jerry");

        model.getPlayerByIndex(0).setPosition(0);
        model.getPlayerByIndex(1).setPosition(0);
        model.getPlayerByIndex(0).getAccount().setBalance(100);
        model.getPlayerByIndex(1).getAccount().setBalance(100);

        view.notifyModel(model);
        gameController.gameTurn();
    }

}
