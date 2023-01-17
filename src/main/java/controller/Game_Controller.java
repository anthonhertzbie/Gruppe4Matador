package controller;

import model.Model;

import java.util.ArrayList;
import java.util.List;

public class Game_Controller {
    private final Model model;
    private UserIO userIO;
    private final ArrayList<Notifier> notifiers = new ArrayList<>();

    private Fieldlogic_Controller fieldlogic;

    private BuyableController buyableLogic;

    /**
     * Saves the model created in Main
     * @param model
     */
    public Game_Controller(Model model){
        this.model = model;
    }

    /**
     * Saves the userIO created in Main.
     * Instantiate the related controllers
     * @param userIO
     */
    public void setUserIO(UserIO userIO) {
        this.userIO = userIO;
        fieldlogic = new Fieldlogic_Controller(model, userIO);
        buyableLogic = new BuyableController(model, userIO);
    }

    /**
     * Updates the View with the current model
     * @param notifier
     */
    public void addNotifier(Notifier notifier){
        this.notifiers.add(notifier);
    }

    /**
     * Updates the list of notifiers. Theres only one here.
     * Updates the View with the current model
     */
    public void notifyEverything(){
        for (Notifier n:notifiers) {
            n.notifyModel(model);
        }
    }


    /**
     * Loops the game main methods to be run.
     *
     */
    public void mainGameLoop(){
        while (true){
            if (model.getPlayerCurrentTurn().getHasLost()){
                model.changeTurn();
            }
            if (!model.getPlayerCurrentTurn().isInJail()) {
                normalTurn();
                playerMoves();
            }
            booleanReset();
            notifierWithLogic();
        }
    }

    /**
     * Determines the game flow in case the player is not in jail.
     * Takes input from the user (View) in regards to the current turn and saves them in the model
     * Is dynamic suchsuch building/selling houses only appears if it is possible
     */
    public void normalTurn(){
        String currentName = model.getPlayerCurrentTurn().getName();
        String choice = "";
        boolean isOwnerOfGroup = false;
        List<Integer> ownedGroups = new ArrayList<>();

        for (int i = 0; i < model.gameBoard().getOwnerOfFieldGroups().length; i++){
            if (model.gameBoard().getOwnerOfFieldGroups()[i] == model.getCurrentTurn()){
                isOwnerOfGroup = true;
                ownedGroups.add(i);
            }
        }
        while(choice != "Rull med tærningerne") {
            if (isOwnerOfGroup && model.getPlayerCurrentTurn().getTotalHouses() == 0) {
                choice = userIO.getUserButtonPressed(currentName + "'s tur.", "Rull med tærningerne", "Byg huse");
                switch (choice) {
                    case "Rull med tærningerne":
                        userIO.showMessage("Rul tærningerne!");
                        return;

                    case "Byg huse":
                        while (true) {
                            buyableLogic.purchaseHouse();
                            model.getPlayerCurrentTurn().setTotalHouses(model.getPlayerCurrentTurn().getTotalHouses() + 1);
                            choice = userIO.getUserButtonPressed("Vil du bygge flere huse?", "Ja", "Nej");
                            if (choice.equals("Nej")) {
                                break;
                            }
                        }
                    }
                }
            else if (isOwnerOfGroup && model.getPlayerCurrentTurn().getTotalHouses() != 0) {
                    choice = userIO.getUserButtonPressed(currentName + "'s tur.", "Rull med tærningerne", "Byg huse", "Sælg huse");
                    switch (choice) {
                        case "Rull med tærningerne":
                            userIO.showMessage("Rul tærningerne!");
                            return;
                        case "Byg huse":
                            while (!choice.equals("Nej")) {
                                buyableLogic.purchaseHouse();
                                model.getPlayerCurrentTurn().setTotalHouses(model.getPlayerCurrentTurn().getTotalHouses() + 1);
                                choice = userIO.getUserButtonPressed("Vil du bygge flere huse?", "Ja", "Nej");
                            }
                            break;
                        case "Sælg huse":
                            while (true) {
                                buyableLogic.sellHouse();
                                model.getPlayerCurrentTurn().setTotalHouses(model.getPlayerCurrentTurn().getTotalHouses() - 1);
                                choice = userIO.getUserButtonPressed("Vil du sælge flere huse?", "Ja", "Nej");
                                if (choice.equals("Nej")) {
                                    break;
                                }
                            }
                    }
                //}
            } else if (!isOwnerOfGroup) {
                choice = userIO.getUserButtonPressed(currentName + "'s tur.", "Rull med tærningerne");
            }
        }
        userIO.updateView(model);
    }


    /**
     * Checks how many times a player has thrown the same dice in a row, and throws him in jail if it is his 3rd double.
     */
    public void checkForDoubleDices(){
        if (model.getCup().getDice1() == model.getCup().getDice2() && model.getPlayerCurrentTurn().getDoubleTurn() < 2){
            userIO.showMessage("Congrats, you get another turn!");
            model.getPlayerCurrentTurn().addDoubleTurn(1);
            model.addCurrentTurn(-1);
        } else if (model.getCup().getDice1() == model.getCup().getDice2() && model.getPlayerCurrentTurn().getDoubleTurn() == 2){
            userIO.showMessage("You're too lucky with the dices... 3rd double in a row... You have been put in jail!");
            model.setPrison(true);
            model.getPlayerCurrentTurn().setPosition(10);
            model.getPlayerCurrentTurn().setDoubleTurn(0);
        } else{
            model.getPlayerCurrentTurn().setDoubleTurn(0);
        }
    }

    /**
     * Rolles the dices and moves the player accordingly
     */
    public void playerMoves(){
        diceRoll();
        model.setPlayerPosition(model.getCup().getSum());
    }

    /**
     * Does a lot.... Read:
     */
    public void notifierWithLogic(){
        // Updates View (UI)
        notifyEverything();
        // Checks what kind of field the player has landed on and acts accordingly
        fieldlogic.specialField();
        // If the player landed on a field which can be bought, it will be handled in here.
        buyableLogic.buyableLogic(model, userIO);
        // Checks
        model.gameBoard().updateFieldGroupsOwned();
        loseCondition();
        checkForDoubleDices();
        notifyEverything();
        model.changeTurn();
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
            if (!model.getPlayerByIndex(i).getHasLost()){
                totalPlayers += 1;
                winner = model.getPlayerByIndex(i).getName();
            }
        }
        if (totalPlayers == 1){
            userIO.showMessage("Game is over. " + winner + " has won!");
            model.setGameIsOver(true);
            notifyEverything();
        }

        for (int i = 0; i < model.getTotalPlayerCount(); i++){
            if (model.getPlayerByIndex(i).getPlayerBalance() < 0){
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

    /**
     * Sets the total amounts of playeres allowed in the current game
     * @param totalPlayerCount the amount of players that will be in the game
     */
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
}
