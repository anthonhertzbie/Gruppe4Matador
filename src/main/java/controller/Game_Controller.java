package controller;

import model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game_Controller {
    private final Model model;
    private UserIO userIO;
    private final ArrayList<Notifier> notifiers = new ArrayList<>();

    private Fieldlogic_Controller fieldlogic;

    private BuyableController buyableLogic;
    public Game_Controller(UserIO userIO, Model model){
        this.userIO = userIO;
        this.model = model;
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
            n.notifyModel(model);
        }
    }
    public void getUserButtonPressed(String message, String ... userOptions){
        userIO.getUserButtonPressed(message, userOptions);
    }

    public void startGame(boolean runGameTurn){
        model.setNormalTurn(true);
        if(runGameTurn){
            gameTurn();
        }

    }

    public void gameTurn(){
        while (true){
            int player = model.getCurrentTurn();
            System.out.println(model.getPlayerCurrentTurn().getName() + "is name current turn");
            if (model.getPlayerCurrentTurn().getHasLost()){
                System.out.println("Player has lost : " + model.getPlayerCurrentTurn().getName());
                model.changeTurn();
            } if (!model.getPlayerCurrentTurn().isInJail()) {
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
        if (isOwnerOfGroup && model.getPlayerCurrentTurn().getTotalHouses() == 0){
            choice = userIO.getUserButtonPressed(currentName + "'s tur.", "Rull med tærningerne", "Byg huse");
            switch(choice) {
                case "Rull med tærningerne":
                    return;
                case "Byg huse":
                    while (true) {
                        buyableLogic.purchaseHouse();
                        model.getPlayerCurrentTurn().setTotalHouses(model.getPlayerCurrentTurn().getTotalHouses() + 1);
                        choice = userIO.getUserButtonPressed("Vil du bygge flere huse?", "Ja", "Nej");

                        if (choice.equals("Nej")) {
                            userIO.showMessage("Rul terningerne!");
                            break;
                        }
                    }
            }
        }
        else if(isOwnerOfGroup && model.getPlayerCurrentTurn().getTotalHouses() != 0){
            choice = userIO.getUserButtonPressed(currentName + "'s tur.", "Rull med tærningerne", "Byg huse", "Sælg huse");
            switch(choice) {
                case "Rull med tærningerne":
                    return;
                case "Byg huse":
                    while (true) {
                        buyableLogic.purchaseHouse();
                        model.getPlayerCurrentTurn().setTotalHouses(model.getPlayerCurrentTurn().getTotalHouses() + 1);
                        for (int i = 0; i < 40; i++) {
                        }
                        choice = userIO.getUserButtonPressed("Vil du bygge flere huse?", "Ja", "Nej");
                        if (choice.equals("Nej")) {
                            userIO.showMessage("Rul terningerne!");
                            break;
                        }
                    }
                case "Sælg huse":
                    while (true) {
                        buyableLogic.sellHouse();
                        model.getPlayerCurrentTurn().setTotalHouses(model.getPlayerCurrentTurn().getTotalHouses() - 1);
                        for (int i = 0; i < 40; i++) {
                        }
                        choice = userIO.getUserButtonPressed("Vil du sælge flere huse?", "Ja", "Nej");
                        if (choice.equals("Nej")) {
                            userIO.showMessage("Rul terningerne!");
                            break;
                        }
                    }
            }
        }
        else if (!isOwnerOfGroup){
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
            //userIO.moveCar(model);
            userIO.showMessage("You're too lucky with the dices... 3rd double in a row... You have been put in jail!");
            model.setPrison(true);
            model.getPlayerCurrentTurn().setPosition(10);
            //userIO.moveCar(model);
            model.getPlayerCurrentTurn().setDoubleTurn(0);
        }
    }

    public void playerMoves(){
        diceRoll();
        model.setPlayerPosition(model.getCup().getSum());
    }

    public void notifierWithLogic(){
        notifyEverything();
        fieldlogic.specialField();
        buyableLogic.buyableLogic(model, userIO);
        model.gameBoard().updateFieldGroupsOwned();
        loseCondition();
        checkForDoubleDices();
        notifyEverything();
        model.changeTurn();
    }

    public void fielLogic(){

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

    /**
     * Sets the lose-condition for the game. Also auctions away all the losing players property.
     */
    public void loseCondition(){
        int totalPlayers = 0;
        String winner = "";
        List<Integer> listOfOwnedFields = new ArrayList<>();

        for (int i = 0; i < model.getTotalPlayerCount(); i++){
            if (model.getPlayerByIndex(i).getHasLost() == false){
                totalPlayers += 1;
                winner = model.getPlayerByIndex(i).getName();
            }
        }
        if (totalPlayers == 1){
            userIO.waitForUserInput("Game is over. " + winner + " has won!");
            model.setGameIsOver(true);
            notifyEverything();
        }

        for (int i = 0; i < model.getTotalPlayerCount(); i++){
            if (model.getPlayerByIndex(i).getValueOfAllAssets() < 0){
                model.getPlayerByIndex(i).setHasLost(true);
                model.getPlayerByIndex(i).getAccount().setBalance(0);
                userIO.showMessage("You have lost :(. You will be removed.");
                for(int k = 0; k < 40; k++){
                    if(model.gameBoard().whoOwnsThis(k)== i){
                        listOfOwnedFields.add(k);
                    }
                }
                for(int j = 0; j < listOfOwnedFields.size(); j++){
                    buyableLogic.auctionFunction(listOfOwnedFields.get(j));
                }
                userIO.removePlayerLost(model);
                notifyEverything();
            }
        }

    }



    // Sets the total amounts of playeres allowed in the current game
    public void setTotalPlayerCount(String totalPlayerCount){
        model.setTotalPlayerCount(Integer.parseInt(totalPlayerCount));
    }


    // Rolls the dices and updates the view
    public void diceRoll(){
        model.getCup().rollDices();
        notifyEverything();
    }
    // Sets the name and index of a given player
    public void setName(int index, String name){
        model.setPlayerName(index, name);
        model.getPlayerByIndex(index).setPlayerID(index);
        notifyEverything();
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
