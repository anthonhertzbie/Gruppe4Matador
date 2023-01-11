package controller;

import model.Model;

import java.util.ArrayList;

public class Game_Controller {
    private final Model model = new Model();
    private UserIO userIO;
    private final ArrayList<Notifier> notifiers = new ArrayList<>();

    private Fieldlogic_Controller fieldlogic;
    public Game_Controller(UserIO userIO){
        this.userIO = userIO;
        this.fieldlogic = new Fieldlogic_Controller(model, userIO);
    }


    public void setUserIO(UserIO userIO) {
        this.userIO = userIO;
        fieldlogic = new Fieldlogic_Controller(model, userIO);
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
            else if (model.getPlayerCurrentTurn().isInJail() == false) {
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
    public void playerMoves(){
        diceRoll();
        notifyEverything();
        model.setPlayerPosition(model.getCup().getSum());
    }

    public void notifierWithLogic(){
        fieldlogic.specialField();
        notifyEverything();
        loseCondition();
        model.changeTurn();
    }

    public void setPlayerFreeFromJail(){
        model.getPlayerCurrentTurn().setInJail(false);
    }

    /**
     * This only needs to be called !!FIRST THIG!! after the current player has moved.
     */
    public void booleanReset(){
        model.resetBooleans();
        model.setBooleans();
    }

    public void loseCondition(){
        for (int i = 0; i < model.getTotalPlayerCount(); i++){
            if (model.getPlayerByIndex(i).getValueOfAllAssets() < 0){
                model.getPlayerByIndex(i).setHasLost(true);
                model.getPlayerByIndex(i).getAccount().setBalance(0);
                userIO.removePlayerLost(model);
            }
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
