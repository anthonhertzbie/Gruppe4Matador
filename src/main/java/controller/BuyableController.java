package controller;

import model.Model;
import model.Player;

import java.util.ArrayList;

public class BuyableController {
    private Model model;
    private UserIO userIO;
    public String[] acceptAbleFieldTypes = {"street", "ferry", "brewery"};

    public BuyableController(Model model, UserIO userIO) {
        this.model = model;
        this.userIO = userIO;
    }

    public boolean fieldAcceptTest(Model model) {
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
        if (fieldAcceptTest(model)) {
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
                        return;
                    case "nej":
                }
            } else {
                payrent(currenPosition);
            }
        }
    }


    private void payrent(int currentPosition) {
        int rentToPay = model.gameBoard().getFieldCurrentRent(currentPosition);
        if (model.gameBoard().whoOwnsThis(currentPosition) != model.getCurrentTurn() && !model.gameBoard().ownerOfAll(model.gameBoard().whoOwnsThis(currentPosition), currentPosition)) {
            userIO.showMessage(model.getPlayerCurrentTurn().getName() + "! DU skal sgu betale rente kammerat!");
            model.getPlayerCurrentTurn().addPlayerBalance(-rentToPay);
        } else if (model.gameBoard().ownerOfAll(model.gameBoard().whoOwnsThis(currentPosition), currentPosition)) {
            userIO.showMessage(model.getPlayerCurrentTurn().getName() + "! DU skal sgu betale DOBBELT rente kammerat!");
            model.getPlayerCurrentTurn().addPlayerBalance(-rentToPay * 2);
        }
    }

    public void purchaseHouse() {
        ArrayList<String> ownedP = new ArrayList<>(0);
        for (int i = 0; i < 40; i++) {
            if (model.gameBoard().whoOwnsThis(i) == model.getCurrentTurn()) {
                ownedP.add(model.gameBoard().getFieldName(i));
            }
        }
        String message = "Hvor vil du gerne bygge et hus?";
        String choice = tooBigSwitchStatement(ownedP, message);

        for (int i = 0; i < 40; i++) {
            if (choice.equals(model.gameBoard().getFieldName(i))) {
                model.getPlayerCurrentTurn().getAccount().payForHouse((model.gameBoard().getSpecificPrice(i, 4)));
                int houses = model.gameBoard().getField(i).getNumOfHouses() + 1;
                model.gameBoard().getField(i).setNumOfHouses(houses);
                userIO.setHouses(i, houses);
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
