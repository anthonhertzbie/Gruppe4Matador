package AcceptTests;
import controller.*;
import model.*;
import view.*;

import java.util.ArrayList;

public class Id11 {

    private final Model model = new Model();
    private final Gameboard gameboard = new Gameboard();
    private UserIO userIO;
    private final ArrayList<Notifier> notifiers = new ArrayList<>();

    private Fieldlogic_Controller fieldlogic;

    private BuyableController buyableLogic;

    public static void main(String[] args) {
        new Id11().run();
    }
    private void run(){
        Game_Controller gameController = new Game_Controller(model);
        View view = new View(gameController);
        gameController.diceRoll();
        gameController.addNotifier(view);
        userIO = new ViewUserIO(view);
        buyableLogic = new BuyableController(model, userIO);
        fieldlogic = new Fieldlogic_Controller(model, userIO);
        gameController.setUserIO(userIO);
        view.setGui_start();
        model.setNormalTurn(true);
        gameController.setTotalPlayerCount("3");
        view.setGuiTotalPlayers(model);
        gameController.setName(0, "Bellis");
        gameController.setName(1, "Bubble");
        gameController.setName(2, "Bloom");

        model.getPlayerByIndex(0).setPosition(18);
        model.getPlayerByIndex(1).setPosition(15);
        model.getPlayerByIndex(2).setPosition(15);
        model.getPlayerByIndex(0).getAccount().setBalance(10000);
        model.getPlayerByIndex(1).getAccount().setBalance(10000);
        model.getPlayerByIndex(2).getAccount().setBalance(10000);
        model.getPlayerByIndex(0).setHasJailCard(true);
        userIO.addJailCard(model);
        view.updateView(model);

        // first turn
        userIO.showMessage("Rul terningerne!");
        model.setPlayerPosition(15);
        view.notifyModel(model);
        gameController.notifyEverything();
        model.getDeck().setFirstCard(44);
        fieldlogic.chanceCardField(model);
        gameController.notifyEverything();
        model.changeTurn();

        // second turn
        userIO.showMessage("Rul terningerne!");
        model.setPlayerPosition(15);
        view.notifyModel(model);
        fieldlogic.jailField(model);
        model.changeTurn();
        view.notifyModel(model);

        // third turn
        userIO.showMessage("Rul terningerne!");
        model.setPlayerPosition(15);
        view.notifyModel(model);
        fieldlogic.jailField(model);
        view.notifyModel(model);
        model.changeTurn();

        // Third turn
        fieldlogic.jailField(model);
        userIO.showMessage("Rul terningerne!");
        model.changeTurn();
        model.getCup().getActualDice1().setFace(4);
        model.getCup().getActualDice2().setFace(6);
        model.setPlayerPosition(model.getCup().getDice1() + model.getCup().getDice2());
        view.updateView(model);

        // Fourth turn
        model.changeTurn();
        userIO.getUserButtonPressed(model.getPlayerCurrentTurn().getName() + " du er stadig i fængsel.", "Betal 1000$ for at komme ud", "Rull med terningerne", "Brug fængselskortet");
        model.getCup().getActualDice1().setFace(5);
        model.getCup().getActualDice2().setFace(5);
        if(model.getCup().getDice1() == model.getCup().getDice2()) {
            view.updateView(model);
            userIO.showMessage("Tillykke du er fri");
            model.getPlayerCurrentTurn().setInJail(false);
            model.getPlayerCurrentTurn().setInJailTurn(0);
        } else{
            userIO.showMessage("too bad... du slog ikke to ens ");
        }
        model.setPlayerPosition(model.getCup().getDice1() + model.getCup().getDice2());
        view.updateView(model);
        model.changeTurn();
        model.getCup().getActualDice1().setFace(4);
        model.getCup().getActualDice2().setFace(6);
        gameController.mainGameLoop();
        }

    }