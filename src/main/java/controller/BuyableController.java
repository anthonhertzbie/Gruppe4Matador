package controller;

import model.Model;
import model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuyableController {
    private Model model;
    private UserIO userIO;
    public String[] acceptAbleFieldTypes = {"street", "ferry", "brewery"};

    /**
     * Gets model and UserIO from GameController
     * @param model
     * @param userIO
     */
    public BuyableController(Model model, UserIO userIO) {
        this.model = model;
        this.userIO = userIO;
    }

    /**
     * Increases the rent on a field with index of the gameboard.
     * Is limited such rent only can be increased if the player owns all the fields in the same color
     * @param i
     */
    public void increaseRentOnField(int i){
        if(model.gameBoard().checkIfFieldGroupOwned(i) && fieldAcceptTestStreet(i)){
            for(int j = 0; j < model.gameBoard().getFieldGroup(i).length; j++){
                userIO.setRentPrice(model.gameBoard().getFieldGroup(i)[j], "Leje: " + model.gameBoard().getFieldCurrentRent(model.gameBoard().getFieldGroup(i)[j])*2);
            }
        }
    }


    /**
     * Helping class for increaseRentOnField. Checks if a field is a street.
     * @param i
     * @return
     */
    public boolean fieldAcceptTestStreet(int i) {
            if (model.gameBoard().getFieldType(i).equals(acceptAbleFieldTypes[0])) {
                return true;
            }

        return false;
    }

    /**
     * Checks if a field can be bought
     * @param model
     * @return
     */
    public boolean fieldAcceptTestAllBuyable(Model model) {
        for (int i = 0; i < acceptAbleFieldTypes.length; i++) {
            if (model.gameBoard().getFieldType(model.getPlayerCurrentTurn().getPosition()).equals(acceptAbleFieldTypes[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Purchasing a field if possible. Auctioning it off if not.
     * @param model
     * @param userIO
     */
    public void purchaseField(Model model, UserIO userIO) {
        int currentPlayer = model.getCurrentTurn();
        int currenPosition = model.getPlayerCurrentTurn().getPosition();
        String userInput;
        if (fieldAcceptTestAllBuyable(model)) {
            if (!model.gameBoard().isOwned(currenPosition)) {

                userIO.updateView(model);

                if (model.getPlayerCurrentTurn().getPlayerBalance() - model.gameBoard().getSpecificPrice(currenPosition, 0) < 0){
                    userIO.showMessage("Du har ikke penge nok til at k??be feltet.");
                    userInput = "nej";
                } else{
                    userInput = userIO.getUserButtonPressed("Vil du k??be dette felt", "ja", "nej");
                }

                switch (userInput) {
                    case "ja":
                        //the amount that needs to be added.
                        int price = model.gameBoard().getSpecificPrice(currenPosition, 0);
                        model.gameBoard().buyField(currenPosition, currentPlayer);
                        model.getPlayerCurrentTurn().addPlayerBalance(-price);
                        userIO.setOwnerBorder(currenPosition, currentPlayer);
                        userIO.setRentPrice(currenPosition, "Leje: " + model.gameBoard().getFieldCurrentRent(currenPosition));
                        model.gameBoard().updateFieldGroupsOwned();
                        increaseRentOnField(currenPosition);
                        return;
                    case "nej":
                        auctionFunction(currenPosition);
                }
            } else {
                payrent(currenPosition);
            }
        }
    }


    /**
     * Controls the flow of an auction.
     * @param fieldOnAuction
     */
    public void auctionFunction(int fieldOnAuction){
        int auctionPrice = 0;
        int currentPlayerIndex = 0;

        List<Integer> playerIndex = new ArrayList<>();

        for (int i = 0; i < model.getTotalPlayerCount(); i++){
            if (!model.getPlayerByIndex(i).getHasLost()){
                playerIndex.add(model.getPlayerByIndex(i).getPlayerID());
            }
        }
        userIO.showMessage("Grunden er r??get p?? auktion!");
        while (true) {
                if (currentPlayerIndex >= playerIndex.size()) {
                    currentPlayerIndex = 0;
                }
                if (playerIndex.size() == 0){
                    userIO.showMessage("Auction is over");
                    return;
                }
            if (model.getPlayerByIndex(playerIndex.get(currentPlayerIndex)).getPlayerBalance() + 1000 > auctionPrice){
            String choice = userIO.getUserButtonPressed(model.getPlayerByIndex(playerIndex.get(currentPlayerIndex)).getName() +
                    "'s tur. Nuv??rende pris er: " + auctionPrice + ". H??v bel??bet med et bel??b eller forlad auktionen: ",
                    "100", "200", "300", "400", "500", "600", "700", "800", "900", "1000", "Leave auction");

            switch (choice) {
                case "100" -> auctionPrice += 100;
                case "200" -> auctionPrice += 200;
                case "300" -> auctionPrice += 300;
                case "400" -> auctionPrice += 400;
                case "500" -> auctionPrice += 500;
                case "600" -> auctionPrice += 600;
                case "700" -> auctionPrice += 700;
                case "800" -> auctionPrice += 800;
                case "900" -> auctionPrice += 900;
                case "1000" -> auctionPrice += 1000;
                case "Leave auction" -> {
                    playerIndex.removeAll(Arrays.asList(playerIndex.get(currentPlayerIndex)));
                    currentPlayerIndex -= 1;
                    if (playerIndex.size() == 1) {

                        if (model.gameBoard().getField(fieldOnAuction).getNumOfHouses() > 0) {
                            int[] fieldGroup = model.gameBoard().getFieldGroup(fieldOnAuction);
                            for (int i = 0; i < fieldGroup.length; i++) {
                                System.out.println("Im here");
                                model.gameBoard().getField(fieldGroup[i]).setCurrentRent(model.gameBoard().getSpecificPrice(fieldOnAuction, 2));
                                model.gameBoard().getField(fieldGroup[i]).setNumOfHouses(0);
                                userIO.setHouses(fieldGroup[i], 0, model.gameBoard().getFieldCurrentRent(fieldGroup[i]), model.gameBoard());
                            }
                        }
                        userIO.showMessage(model.getPlayerByIndex(playerIndex.get(0)).getName() + " har vundet auktionen!" + " han betalte " + auctionPrice + "kr");
                        model.getPlayerByIndex(playerIndex.get(0)).addPlayerBalance(-auctionPrice);
                        model.gameBoard().buyField(fieldOnAuction, playerIndex.get(0));
                        increaseRentOnField(fieldOnAuction);
                        userIO.setOwnerBorder(fieldOnAuction, playerIndex.get(0));
                        userIO.setRentPrice(fieldOnAuction, "Leje: " + model.gameBoard().getFieldCurrentRent(fieldOnAuction));
                        model.gameBoard().updateFieldGroupsOwned();

                        if (model.gameBoard().checkIfFieldGroupOwned(fieldOnAuction)) {
                            for (int i = 0; i < model.gameBoard().getFieldGroup(fieldOnAuction).length; i++) {
                                userIO.setRentPrice(model.gameBoard().getFieldGroup(fieldOnAuction)[i], "Leje: " + model.gameBoard().getFieldCurrentRent(fieldOnAuction) * 2);

                            }
                        }

                        return;
                    }
                }
                }
            } else{
                playerIndex.removeAll(Arrays.asList(playerIndex.get(currentPlayerIndex)));
                currentPlayerIndex -= 1;
                userIO.showMessage("Du er for fattig");
                if (playerIndex.size() == 0){
                    return;
                }

            }
            currentPlayerIndex += 1;

        }
    }


    /**
     * Makes people pay rent depending on field
     * @param currentPosition
     */
    private void payrent(int currentPosition) {
        int rentToPay = model.gameBoard().getFieldCurrentRent(currentPosition);
        if(model.gameBoard().isOwned(currentPosition)) {
            if (model.gameBoard().whoOwnsThis(currentPosition) != model.getCurrentTurn() && !model.gameBoard().checkIfFieldGroupOwned(currentPosition)) {
                userIO.showMessage(model.getPlayerCurrentTurn().getName() + "! DU skal sgu betale rente kammerat!");
                model.getPlayerCurrentTurn().addPlayerBalance(-rentToPay);
                model.getPlayerByIndex(model.gameBoard().whoOwnsThis(currentPosition)).addPlayerBalance(rentToPay);
            } else if (model.gameBoard().checkIfFieldGroupOwned(currentPosition) && !model.gameBoard().checkForHouse(currentPosition)) {
                userIO.showMessage(model.getPlayerCurrentTurn().getName() + "! DU skal sgu betale DOBBELT rente kammerat!");
                model.getPlayerCurrentTurn().addPlayerBalance(-rentToPay * 2);
                model.getPlayerByIndex(model.gameBoard().whoOwnsThis(currentPosition)).addPlayerBalance(rentToPay * 2);
            } else if (model.gameBoard().checkIfFieldGroupOwned(currentPosition) && model.gameBoard().checkForHouse(currentPosition)){
                userIO.showMessage(model.getPlayerCurrentTurn().getName() + "! DU skal sgu betale HUS rente kammerat!");
                model.getPlayerCurrentTurn().addPlayerBalance(-rentToPay);
                model.getPlayerByIndex(model.gameBoard().whoOwnsThis(currentPosition)).addPlayerBalance(rentToPay);

            }
        }
    }

    /**
     * Allows you to purchase a house
     */
    public void purchaseHouse() {
        int houses;
        ArrayList<String> ownedP = new ArrayList<>(0);
        for (int i = 0; i < 40; i++) {
            if (model.gameBoard().whoOwnsThis(i) == model.getCurrentTurn()) {
                ownedP.add(model.gameBoard().getFieldName(i));
            }
        }
        ownedP.remove("Helsing??r - Helsingborg");
        ownedP.remove("Mols-Linjen");
        ownedP.remove("Tuborg Squash");
        ownedP.remove("Gedser - Rostock");
        ownedP.remove("Coca Cola");
        ownedP.remove("R??dby - Puttgarden");
        String message = "Hvor vil du gerne bygge et hus?";
        String choice = tooBigSwitchStatement(ownedP, message);

        for (int i = 0; i < 40; i++) {
            if (choice.equals(model.gameBoard().getFieldName(i))) {
                model.getPlayerCurrentTurn().getAccount().payForHouse((model.gameBoard().getSpecificPrice(i, 1)));
                model.getPlayerCurrentTurn().setTotalHouses(5);
                model.gameBoard().rentIncrease(i);
                houses = model.gameBoard().getField(i).getNumOfHouses();
                userIO.setHouses(i, houses, model.gameBoard().getFieldCurrentRent(i), model.gameBoard());

            }
        }
    }

    /**
     * Allows you to sell a house, if you have a house.
     */
    public void sellHouse() {
        System.out.println("S??lger Huse");
        ArrayList<String> ownedP = new ArrayList<>(0);
        for (int i = 0; i < 40; i++) {
            if (model.gameBoard().whoOwnsThis(i) == model.getCurrentTurn()) {
                ownedP.add(model.gameBoard().getFieldName(i));
            }
        }
        ownedP.remove("Helsing??r - Helsingborg");
        ownedP.remove("Mols-Linjen");
        ownedP.remove("Tuborg Squash");
        ownedP.remove("Gedser - Rostock");
        ownedP.remove("Coca Cola");
        ownedP.remove("R??dby - Puttgarden");
        String message = "Hvor vil du gerne s??lge et hus?";
        String choice = tooBigSwitchStatement(ownedP, message);

        for (int i = 0; i < 40; i++) {
            try {
                if (choice.equals(model.gameBoard().getFieldName(i))) {
                    System.out.println("Actually selling " + model.gameBoard().getFieldCurrentRent(i));
                    System.out.println(model.gameBoard().getFieldCurrentRent(i) + " this is it " + model.gameBoard().getSpecificPrice(i, 2));
                    if (model.gameBoard().getFieldCurrentRent(i) == model.gameBoard().getSpecificPrice(i, 2)){
                        userIO.showMessage("Du har ingen huse p?? feltet.");
                    }
                    else {
                        model.getPlayerCurrentTurn().getAccount().sellHouse((model.gameBoard().getSpecificPrice(i, 1)));
                        model.gameBoard().rentDecrease(i);
                        int houses = model.gameBoard().getField(i).getNumOfHouses();
                        userIO.setHouses(i, houses, model.gameBoard().getFieldCurrentRent(i), model.gameBoard());
                        System.out.println(model.gameBoard().getFieldCurrentRent(i) + " first is currentRen " + model.gameBoard().getSpecificPrice(i, 2));
                        if (model.gameBoard().getFieldCurrentRent(i) == model.gameBoard().getSpecificPrice(i, 2)) {
                            userIO.setHouses(i, houses, model.gameBoard().getFieldCurrentRent(i) * 2, model.gameBoard());
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                userIO.showMessage("Du har ikke nogle huse p?? dette felt");
            }
        }
    }

    /**
     * Copied from GUI
     * @param ownedP
     * @param message
     * @return
     */
    private String tooBigSwitchStatement(ArrayList<String> ownedP, String message) {
        String choice = "";
        switch (ownedP.size()) {
            case 0 -> {
                return choice;
            }
            case 1 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0));
                return choice;
            }
            case 2 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1));
                return choice;
            }
            case 3 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1), ownedP.get(2));
                return choice;
            }
            case 4 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1), ownedP.get(2), ownedP.get(3));
                return choice;
            }
            case 5 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4));
                return choice;
            }
            case 6 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5));
                return choice;
            }
            case 7 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6));
                return choice;
            }
            case 8 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6),
                        ownedP.get(7));
                return choice;
            }
            case 9 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6),
                        ownedP.get(7), ownedP.get(8));
                return choice;
            }
            case 10 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6),
                        ownedP.get(7), ownedP.get(8), ownedP.get(9));
                return choice;
            }
            case 11 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6),
                        ownedP.get(7), ownedP.get(8), ownedP.get(9), ownedP.get(10));
                return choice;
            }
            case 12 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6),
                        ownedP.get(7), ownedP.get(8), ownedP.get(9), ownedP.get(10), ownedP.get(11));
                return choice;
            }
            case 13 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6),
                        ownedP.get(7), ownedP.get(8), ownedP.get(9), ownedP.get(10), ownedP.get(11), ownedP.get(12));
                return choice;
            }
            case 14 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6),
                        ownedP.get(7), ownedP.get(8), ownedP.get(9), ownedP.get(10), ownedP.get(11),
                        ownedP.get(12), ownedP.get(13));
                return choice;
            }
            case 15 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6),
                        ownedP.get(7), ownedP.get(8), ownedP.get(9), ownedP.get(10), ownedP.get(11),
                        ownedP.get(12), ownedP.get(13), ownedP.get(14));
                return choice;
            }
            case 16 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6),
                        ownedP.get(7), ownedP.get(8), ownedP.get(9), ownedP.get(10), ownedP.get(11),
                        ownedP.get(12), ownedP.get(13), ownedP.get(14), ownedP.get(15));
                return choice;
            }
            case 17 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6),
                        ownedP.get(7), ownedP.get(8), ownedP.get(9), ownedP.get(10), ownedP.get(11),
                        ownedP.get(12), ownedP.get(13), ownedP.get(14), ownedP.get(15), ownedP.get(16));
                return choice;
            }
            case 18 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6),
                        ownedP.get(7), ownedP.get(8), ownedP.get(9), ownedP.get(10), ownedP.get(11),
                        ownedP.get(12), ownedP.get(13), ownedP.get(14), ownedP.get(15), ownedP.get(16),
                        ownedP.get(17));
                return choice;
            }
            case 19 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6),
                        ownedP.get(7), ownedP.get(8), ownedP.get(9), ownedP.get(10), ownedP.get(11),
                        ownedP.get(12), ownedP.get(13), ownedP.get(14), ownedP.get(15), ownedP.get(16),
                        ownedP.get(17), ownedP.get(18));
                return choice;
            }
            case 20 -> {
                choice = userIO.getUserButtonPressed(message, ownedP.get(0), ownedP.get(1),
                        ownedP.get(2), ownedP.get(3), ownedP.get(4), ownedP.get(5), ownedP.get(6),
                        ownedP.get(7), ownedP.get(8), ownedP.get(9), ownedP.get(10), ownedP.get(11),
                        ownedP.get(12), ownedP.get(13), ownedP.get(14), ownedP.get(15), ownedP.get(16),
                        ownedP.get(17), ownedP.get(18), ownedP.get(19));
                return choice;
            }
        }
        return choice;

    }
}
