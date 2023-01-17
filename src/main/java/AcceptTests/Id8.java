package AcceptTests;
import controller.*;
import model.*;
import view.*;

import java.util.ArrayList;

public class Id8 {

    private final Model model = new Model();
    private final Gameboard gameboard = new Gameboard();
    private UserIO userIO;
    private final ArrayList<Notifier> notifiers = new ArrayList<>();

    private Fieldlogic_Controller fieldlogic;

    private BuyableController buyableLogic;

    public static void main(String[] args) {
        new Id8().run();
    }
    private void run(){
        Game_Controller gameController = new Game_Controller(model);
        View view = new View(gameController);
        gameController.addNotifier(view);
        userIO = new ViewUserIO(view);
        buyableLogic = new BuyableController(model, userIO);
        gameController.setUserIO(userIO);
        view.setGui_start();
        model.setNormalTurn(true);
        gameController.setTotalPlayerCount("2");
        view.setGuiTotalPlayers(model);
        gameController.setName(0, "Tom");
        gameController.setName(1, "Jerry");

        model.getPlayerByIndex(1).setPosition(0);
        model.getPlayerByIndex(0).getAccount().setBalance(10000);
        model.getPlayerByIndex(1).getAccount().setBalance(10000);

        for (int i = 0; i < 40; i++) {
            model.getPlayerByIndex(0).setPosition(i);
            int currentPos = model.getPlayerByIndex(0).getPosition();
            if (buyableLogic.fieldAcceptTestAllBuyable(model)){
                model.gameBoard().buyField(i, 0);
                buyableLogic.purchaseField(model,userIO);
                userIO.setOwnerBorder(currentPos,0);
                userIO.setRentPrice(i, "Leje: " + model.gameBoard().getFieldCurrentRent(i) * 2);
            }
        }
        for (int i = 0; i < 8; i++) {
            gameboard.setOwnerOfFieldgroups(0,i);
        }

        model.getPlayerByIndex(0).setPosition(0);


        view.notifyModel(model);
        gameController.mainGameLoop();
    }



}
