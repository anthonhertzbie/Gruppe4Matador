package controller;

import model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            if (model.getPlayerCurrentTurn().getHasLost()){
                model.changeTurn();
            } else if (!model.getPlayerCurrentTurn().isInJail()) {
                normalTurn();
                playerMoves();
            }
            booleanReset();
            notifierWithLogic();

        }
    }
    private void normalTurn(){
        String currentName = model.getPlayerCurrentTurn().getName();
        String choice;
        int currentPosition = model.getPlayerCurrentTurn().getPosition();
        boolean isOwnerOfGroup = false;
        List<Integer> ownedGroups = new ArrayList<>();

        for (int i = 0; i < model.gameBoard().getOwnerOfFieldGroups().length; i++){
            if (model.gameBoard().getOwnerOfFieldGroups()[i] == model.getCurrentTurn()){
                isOwnerOfGroup = true;
                ownedGroups.add(i);
            }
        }
        if (isOwnerOfGroup){
            choice = userIO.getUserButtonPressed(currentName + "'s tur.", "Rull med tærningerne", "Byg huse");
            switch(choice) {
                case "Rull med tærningerne":
                    return;
                case "Byg huse":
                    buyableLogic.purchaseHouse();
                    System.out.println(model.getCurrentTurn());
                    for (int i = 0; i < 40; i++) {
                        System.out.println(model.gameBoard().getFieldName(i) + " " + model.gameBoard().whoOwnsThis(i));
                    }
                    break;
            }

        } else if (!isOwnerOfGroup){
            choice = userIO.getUserButtonPressed(currentName + "'s tur.", "Rull med tærningerne");
        }

        userIO.updateView(model);
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
        model.setPlayerPosition(model.getCup().getSum());
    }

    public void notifierWithLogic(){
        userIO.moveCar(model);
        fieldlogic.specialField();
        buyableLogic.buyableLogic(model, userIO);
        model.gameBoard().updateFieldGroupsOwned();
        System.out.println(model.gameBoard().getOwnerOfFieldGroups()[0]);
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



/*
    public void checkIfGroupsIsOwned(){
        Gameboard board = model.gameBoard();

        for (int i = 0; i < board.getOwnerOfFieldGroups().length; i ++) {
            switch (field) {
                case 1, 3 -> {
                    if (board.whoOwnsThis(1) == board.whoOwnsThis(3) && board.whoOwnsThis(1) == i) {
                        board.setOwnerOfFieldgroups(i, 0);
                    } else if (board.whoOwnsThis(1) != board.whoOwnsThis(3)){
                        board.setOwnerOfFieldgroups(-1, 0);
                    }
                    return;
                }
                case 6, 8, 9 -> {
                    if (board.whoOwnsThis(6) == board.whoOwnsThis(8) && board.whoOwnsThis(8) == board.whoOwnsThis(9) && board.whoOwnsThis(6) == i) {
                        board.setOwnerOfFieldgroups(i, 1);
                    } else if (board.whoOwnsThis(6) != board.whoOwnsThis(8) || board.whoOwnsThis(8) != board.whoOwnsThis(9)){
                        board.setOwnerOfFieldgroups(-1, 1);
                    }
                    return;
                }
                case 11, 13, 14 -> {
                    if (board.whoOwnsThis(11) == board.whoOwnsThis(13) && board.whoOwnsThis(13) == board.whoOwnsThis(14) && board.whoOwnsThis(11) == i) {
                        board.setOwnerOfFieldgroups(i, 2);
                    } else if (board.whoOwnsThis(11) != board.whoOwnsThis(13) || board.whoOwnsThis(13) != board.whoOwnsThis(14)){
                        board.setOwnerOfFieldgroups(-1, 2);
                    }
                    return;
                }

                case 16, 18, 19 -> {
                    if (board.whoOwnsThis(16) == board.whoOwnsThis(18) && board.whoOwnsThis(18) == board.whoOwnsThis(19) && board.whoOwnsThis(16) == i) {
                        board.setOwnerOfFieldgroups(i, 3);
                    } else if (board.whoOwnsThis(16) != board.whoOwnsThis(18) || board.whoOwnsThis(18) != board.whoOwnsThis(19)){
                        board.setOwnerOfFieldgroups(-1, 3);
                    }
                    return;
                }
                case 21, 23, 24 -> {
                    if (board.whoOwnsThis(21) == board.whoOwnsThis(23) && board.whoOwnsThis(23) == board.whoOwnsThis(24) && board.whoOwnsThis(21) == i) {
                        board.setOwnerOfFieldgroups(i, 4);
                    } else if (board.whoOwnsThis(21) != board.whoOwnsThis(23) || board.whoOwnsThis(23) != board.whoOwnsThis(24)){
                        board.setOwnerOfFieldgroups(-1, 4);
                    }
                    return;
                }
                case 26, 27, 29 -> {
                    if (board.whoOwnsThis(26) == board.whoOwnsThis(27) && board.whoOwnsThis(27) == board.whoOwnsThis(29) && board.whoOwnsThis(26) == i) {
                        board.setOwnerOfFieldgroups(i, 5);
                    } else if (board.whoOwnsThis(26) != board.whoOwnsThis(27) || board.whoOwnsThis(27) != board.whoOwnsThis(29)){
                        board.setOwnerOfFieldgroups(-1, 5);
                    }
                    return;
                }
                case 31, 32, 34 -> {
                    if (board.whoOwnsThis(31) == board.whoOwnsThis(32) && board.whoOwnsThis(32) == board.whoOwnsThis(34) && board.whoOwnsThis(31) == i) {
                        board.setOwnerOfFieldgroups(i, 6);
                    } else if (board.whoOwnsThis(31) != board.whoOwnsThis(32) || board.whoOwnsThis(32) != board.whoOwnsThis(34)){
                        board.setOwnerOfFieldgroups(-1, 6);
                    }
                    return;
                }
                case 37, 39 -> {
                    if (board.whoOwnsThis(37) == board.whoOwnsThis(39) && board.whoOwnsThis(37) == i) {
                        board.setOwnerOfFieldgroups(i, 7);
                    } else if (board.whoOwnsThis(37) != board.whoOwnsThis(38)){
                        board.setOwnerOfFieldgroups(-1, 7);
                    }
                    return;
                }
            }
        }
    }

 */



}
