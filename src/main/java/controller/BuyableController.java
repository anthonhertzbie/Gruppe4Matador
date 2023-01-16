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

    public BuyableController(Model model, UserIO userIO) {
        this.model = model;
        this.userIO = userIO;
    }
    public void checkForAllOwned(int i){
        System.out.println("Check for all is running");

        System.out.println(model.gameBoard().checkIfFieldGroupOwned(i) + " is gameboard true?" + " " + fieldAcceptTestStreet(i) + " is street good?");
            if(model.gameBoard().checkIfFieldGroupOwned(i) && fieldAcceptTestStreet(i)){
                System.out.println("Check for all is running 2");
                for(int j = 0; j < model.gameBoard().getFieldGroup(i).length; j++){
                    userIO.setRentPrice(model.gameBoard().getFieldGroup(i)[j], "Leje: " + model.gameBoard().getFieldCurrentRent(model.gameBoard().getFieldGroup(i)[j])*2);
                }

            } else if(!model.gameBoard().checkIfFieldGroupOwned(i) && fieldAcceptTestStreet(i)) {
                System.out.println("is running");
                System.out.println(model.gameBoard().getFieldCurrentRent(i) + ": is field current rent" + " " + model.gameBoard().getSpecificPrice(i,2) + ": is wishing price");
                if (model.gameBoard().getFieldCurrentRent(i)*2 > model.gameBoard().getSpecificPrice(i, 2)) {
                    System.out.println("is running 2");
                    for (int j = 0; j < model.gameBoard().getFieldGroup(i).length; j++) {
                        userIO.setRentPrice(model.gameBoard().getFieldGroup(i)[j], "Leje: " + model.gameBoard().getFieldCurrentRent(model.gameBoard().getFieldGroup(i)[j]) / 2);
                    }
                }
            }

        }
    public boolean fieldAcceptTestStreet(int i) {
            if (model.gameBoard().getFieldType(i).equals(acceptAbleFieldTypes[0])) {
                return true;
            }

        return false;
    }


    public boolean fieldOwnableCheck(Model model) {
        System.out.println("Running");
        for (int i = 0; i < acceptAbleFieldTypes.length; i++) {
            System.out.println(model.gameBoard().getFieldType(model.getPlayerCurrentTurn().getPosition()) + " field type");
            System.out.println(acceptAbleFieldTypes[i]);
            System.out.println(model.gameBoard().getFieldType(model.getPlayerCurrentTurn().getPosition()).equals(acceptAbleFieldTypes[i]));
            System.out.println();
            if (model.gameBoard().getFieldType(model.getPlayerCurrentTurn().getPosition()).equals(acceptAbleFieldTypes[i])) {
                return true;
            }
        }
        return false;
    }

    public void buyableLogic(Model model, UserIO userIO) {
        purchaseField(model, userIO);
    }

    private void purchaseField(Model model, UserIO userIO) {
        int currentPlayer = model.getCurrentTurn();
        int currenPosition = model.getPlayerCurrentTurn().getPosition();
        if (fieldOwnableCheck(model)) {
            if (!model.gameBoard().isOwned(currenPosition)) {

                userIO.moveCar(model);
                userIO.updateView(model);
                String userInput = userIO.getUserButtonPressed("Vil du købe dette felt", "ja", "nej");
                switch (userInput) {
                    case "ja":
                        //the amount that needs to be added.
                        int price = model.gameBoard().getSpecificPrice(currenPosition, 0);
                        model.gameBoard().buyField(currenPosition, currentPlayer);
                        model.getPlayerCurrentTurn().addPlayerBalance(-price);
                        userIO.setOwnerBorder(currenPosition, currentPlayer);
                        userIO.setRentPrice(currenPosition, "Leje: " + model.gameBoard().getFieldCurrentRent(currenPosition));
                        model.gameBoard().updateFieldGroupsOwned();
                        checkForAllOwned(currenPosition);
                        return;
                    case "nej":
                        auctionFunction(currenPosition);
                }
            } else {
                payrent(currenPosition);
            }
        }
    }


    public void auctionFunction(int fieldOnAuction){
        int auctionPrice = 0;
        int currentPlayerIndex = 0;
        String sssss = model.getPlayerByIndex(0).getName();
        System.out.println(sssss + " is a name?");

        List<Integer> playerIndex = new ArrayList<>();

        for (int i = 0; i < model.getTotalPlayerCount(); i++){
            System.out.println("player by index: " + model.getPlayerByIndex(i).getName() + "   " + model.getPlayerByIndex(i).getPlayerID());
            if (!model.getPlayerByIndex(i).getHasLost()){
                playerIndex.add(model.getPlayerByIndex(i).getPlayerID());
            }
        }
        System.out.println(playerIndex.get(2));

        userIO.showMessage("Grunden er røget på auktion!");

        while (true){
            if (currentPlayerIndex >= playerIndex.size()){
                currentPlayerIndex = 0;
            }
            System.out.println(currentPlayerIndex + " is current playerindex" + playerIndex.get(currentPlayerIndex) + " is other number");
            if (true) {

                String choice = userIO.getUserButtonPressed(model.getPlayerByIndex(playerIndex.get(currentPlayerIndex)).getName() + "'s tur. Nuværende pris er: " + auctionPrice + ". Hæv beløbet med et beløb eller forlad auktionen: ", "100", "200", "300", "400", "500", "600", "700", "800", "900", "1000", "Leave auction");

                switch (choice) {
                    case "100":
                        auctionPrice += 100;
                        break;
                    case "200":
                        auctionPrice += 200;
                        break;
                    case "300":
                        auctionPrice += 300;
                        break;
                    case "400":
                        auctionPrice += 400;
                        break;
                    case "500":
                        auctionPrice += 500;
                        break;
                    case "600":
                        auctionPrice += 600;
                        break;
                    case "700":
                        auctionPrice += 700;
                        break;
                    case "800":
                        auctionPrice += 800;
                        break;
                    case "900":
                        auctionPrice += 900;
                        break;
                    case "1000":
                        auctionPrice += 1000;
                        break;
                    case "Leave auction":

                        playerIndex.removeAll(Arrays.asList(playerIndex.get(currentPlayerIndex)));
                        currentPlayerIndex -= 1;
                        System.out.println(playerIndex.size() + " is new size of list");

                        if(playerIndex.size() == 1){
                            userIO.showMessage(model.getPlayerByIndex(playerIndex.get(0)).getName() + " har vundet auktionen!" + " han betalte " + auctionPrice + "kr");
                            model.getPlayerByIndex(playerIndex.get(0)).addPlayerBalance(-auctionPrice);
                            model.gameBoard().buyField(fieldOnAuction, playerIndex.get(0));
                            userIO.setOwnerBorder(fieldOnAuction, playerIndex.get(0));
                            model.gameBoard().updateFieldGroupsOwned();
                            checkForAllOwned(fieldOnAuction);
                            model.gameBoard().updateFieldGroupsOwned();
                            return;
                        }


                }
                currentPlayerIndex += 1;
            }

        }
    }





    private void payrent(int currentPosition) {
        int rentToPay = model.gameBoard().getFieldCurrentRent(currentPosition);
        if(model.gameBoard().isOwned(currentPosition)) {
            if (model.gameBoard().whoOwnsThis(currentPosition) != model.getCurrentTurn() && !model.gameBoard().checkIfFieldGroupOwned(currentPosition)) {
                userIO.showMessage(model.getPlayerCurrentTurn().getName() + "! DU skal sgu betale rente kammerat!");
                model.getPlayerCurrentTurn().addPlayerBalance(-rentToPay);
                model.getPlayerByIndex(model.gameBoard().whoOwnsThis(currentPosition)).getAccount().addBalance(rentToPay);
            } else if (model.gameBoard().checkIfFieldGroupOwned(currentPosition) && !model.gameBoard().checkForHouse(currentPosition)) {
                userIO.showMessage(model.getPlayerCurrentTurn().getName() + "! DU skal sgu betale DOBBELT rente kammerat!");
                model.getPlayerCurrentTurn().addPlayerBalance(-rentToPay * 2);
                model.getPlayerByIndex(model.gameBoard().whoOwnsThis(currentPosition)).getAccount().addBalance(rentToPay * 2);
            } else if (model.gameBoard().checkIfFieldGroupOwned(currentPosition) && model.gameBoard().checkForHouse(currentPosition)){
                userIO.showMessage(model.getPlayerCurrentTurn().getName() + "! DU skal sgu betale HUS rente kammerat!");
                model.getPlayerCurrentTurn().addPlayerBalance(-rentToPay);
                model.getPlayerByIndex(model.gameBoard().whoOwnsThis(currentPosition)).getAccount().addBalance(rentToPay);


            }
        }
    }

    public void purchaseHouse() {
        ArrayList<String> ownedP = new ArrayList<>(0);
        for (int i = 0; i < 40; i++) {
            if (model.gameBoard().whoOwnsThis(i) == model.getCurrentTurn()) {
                ownedP.add(model.gameBoard().getFieldName(i));
            }
        }
        ownedP.remove("Helsingør - Helsingborg");
        ownedP.remove("Mols-Linjen");
        ownedP.remove("Tuborg Squash");
        ownedP.remove("Gedser - Rostock");
        ownedP.remove("Coca Cola");
        ownedP.remove("Rødby - Puttgarden");
        String message = "Hvor vil du gerne bygge et hus?";
        String choice = tooBigSwitchStatement(ownedP, message);

        for (int i = 0; i < 40; i++) {
            if (choice.equals(model.gameBoard().getFieldName(i))) {
                model.getPlayerCurrentTurn().getAccount().payForHouse((model.gameBoard().getSpecificPrice(i, 4)));
                int houses = model.gameBoard().getField(i).getNumOfHouses() + 1;
                model.gameBoard().rentIncrease(i);
                model.gameBoard().getField(i).setNumOfHouses(houses);
                userIO.setHouses(i, houses, model.gameBoard().getFieldCurrentRent(i));

            }
        }
    }

    public void sellHouse() {
        ArrayList<String> ownedP = new ArrayList<>(0);
        for (int i = 0; i < 40; i++) {
            if (model.gameBoard().whoOwnsThis(i) == model.getCurrentTurn()) {
                ownedP.add(model.gameBoard().getFieldName(i));
            }
        }
        ownedP.remove("Helsingør - Helsingborg");
        ownedP.remove("Mols-Linjen");
        ownedP.remove("Tuborg Squash");
        ownedP.remove("Gedser - Rostock");
        ownedP.remove("Coca Cola");
        ownedP.remove("Rødby - Puttgarden");
        String message = "Hvor vil du gerne sælge et hus?";
        String choice = tooBigSwitchStatement(ownedP, message);

        for (int i = 0; i < 40; i++) {
            try {
                if (choice.equals(model.gameBoard().getFieldName(i))) {
                    model.gameBoard().rentIncrease(-i);
                    model.getPlayerCurrentTurn().getAccount().sellHouse((model.gameBoard().getSpecificPrice(i, 4)));
                    int houses = model.gameBoard().getField(i).getNumOfHouses() - 1;
                    model.gameBoard().getField(i).setNumOfHouses(houses);
                    userIO.setHouses(i, houses, model.gameBoard().getFieldCurrentRent(i + 2));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                userIO.showMessage("You don't have any houses on that field");
            }
        }
    }

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
