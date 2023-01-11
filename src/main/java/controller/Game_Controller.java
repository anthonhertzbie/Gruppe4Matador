package controller;

import model.Model;

import java.util.ArrayList;

public class Game_Controller {
    private final Model model = new Model();
    private UserIO userIO;
    private final ArrayList<Notifier> notifiers = new ArrayList<>();

    private Fieldlogic_Controller fieldlogic;

    private BuyableController buyableLogic;
    public Game_Controller(UserIO userIO){
        this.userIO = userIO;
        fieldlogic = new Fieldlogic_Controller(model, userIO);
        buyableLogic = new BuyableController(model, userIO);
    }

    public void setUserIO(UserIO userIO) {
        this.userIO = userIO;
        fieldlogic = new Fieldlogic_Controller(model, userIO);
        buyableLogic = new BuyableController(model, userIO);
    }

    public void addNotifier(Notifier notifier){
        this.notifiers.add(notifier);
    }

    public void notifyEverything(){
        for (Notifier n:notifiers) {
            n.startGame(model);
        }
    }
    public void getUserButtonPressed(String message, String ... userOptions){
        userIO.getUserButtonPressed(message, userOptions);
    }

    public void startGame(){
        model.setStartGUI(true);
        notifyEverything();
        model.setStartGUI(false);
        model.setNormalTurn(true);
        gameTurn();
    }

    public void gameTurn(){
        while (true){
            String currentName = model.getPlayerCurrentTurn().getName();
            if (model.getPlayerCurrentTurn().getHasLost()){
                model.changeTurn();
            }
            else if (!model.getPlayerCurrentTurn().isInJail()) {
                userIO.waitForUserInput(currentName + "'s turn: Press ok to roll dice");
                playerMoves();
                booleanReset();
                notifierWithLogic();
            } else{
                booleanReset();
                notifierWithLogic();
            }

        }
    }

    public void checkForDoubleDices(){
        if (model.getCup().getDice1() == model.getCup().getDice2() && model.getPlayerCurrentTurn().getDoubleTurn() < 2){
            userIO.showMessage("Congrats, you get another turn!");
            model.getPlayerCurrentTurn().addDoubleTurn(1);
            model.addCurrentTurn(-1);
        } else if (model.getCup().getDice1() == model.getCup().getDice2() && model.getPlayerCurrentTurn().getDoubleTurn() == 2){
            userIO.moveCar(model);
            userIO.showMessage("You're too lucky with the dices... 3rd double in a row... You have been put in jail!");
            model.setPrison(true);
            model.getPlayerCurrentTurn().setPosition(10);
            userIO.moveCar(model);
            model.getPlayerCurrentTurn().setDoubleTurn(0);


        }
    }

    public void playerMoves(){
        diceRoll();
        notifyEverything();
        model.setPlayerPosition(model.getCup().getSum());
    }

    public void notifierWithLogic(){
        buyableLogic.buyableLogic(model, userIO);
        fieldlogic.specialField();
        notifyEverything();
        loseCondition();
        checkForDoubleDices();
        model.changeTurn();
    }

    public void setPlayerFreeFromJail(){
        model.getPlayerCurrentTurn().setInJail(false);
    }

    /**
     * This only needs to be called !!FIRST THING!! after the current player has moved.
     */
    public void booleanReset(){
        model.resetBooleans();
        model.setBooleans();
    }

    public void loseCondition(){
        int playerInGame = 0;
        String winner = "";
        for (int i = 0; i < model.getTotalPlayerCount(); i++){
            if (model.getPlayerByIndex(i).getValueOfAllAssets() < 0){
                model.getPlayerByIndex(i).setHasLost(true);
                model.getPlayerByIndex(i).getAccount().setBalance(0);
                userIO.showMessage("You have lost :(. You will be removed.");
                userIO.removePlayerLost(model);
            }
        }
        for (int i = 0; i < model.getTotalPlayerCount(); i++){
            if (model.getPlayerByIndex(i).getHasLost() == false){
                playerInGame += 1;
                winner = model.getPlayerByIndex(i).getName();
            }
        }
        if (playerInGame == 1){
            userIO.waitForUserInput("Game is over. " + winner + " has won!");
            model.setGameIsOver(true);
            notifyEverything();
        }
    }

    public void playerOutOfGame(){
        if (model.getPlayerCurrentTurn().getHasLost() == true){
            model.changeTurn();
        }
    }

    public void editTurn(int number){
        model.addCurrentTurn(number);
    }

    public void setTotalPlayerCount(String totalPlayerCount){
        model.setTotalPlayerCount(Integer.parseInt(totalPlayerCount));
    }

    public void addPlayerBalance(int balance){
        model.getPlayerCurrentTurn().addPlayerBalance(balance);
    }

    public void diceRoll(){
        model.getCup().rollDices();
    }

    public void setName(int index, String name){
        model.setPlayerName(index, name);
    }

}
