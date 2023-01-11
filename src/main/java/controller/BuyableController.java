package controller;

import model.Model;
import model.Player;

public class BuyableController {
    private Model model;
    private UserIO userIO;
    public String[] acceptAbleFieldTypes = {"street", "ferry", "brewery"};

    public BuyableController(Model model, UserIO userIO){
        this.model = model;
        this.userIO = userIO;
    }

    public boolean fieldAcceptTest(Model model) {
        System.out.println("Running");
        for (int i = 0; i < acceptAbleFieldTypes.length; i++){
            System.out.println(model.gameBoard().getFieldType(model.getPlayerCurrentTurn().getPosition()) + " field type");
            System.out.println(acceptAbleFieldTypes[i]);
            System.out.println(model.gameBoard().getFieldType(model.getPlayerCurrentTurn().getPosition()).equals(acceptAbleFieldTypes[i]));
            System.out.println();
            if (model.gameBoard().getFieldType(model.getPlayerCurrentTurn().getPosition()).equals(acceptAbleFieldTypes[i])){
                return true;
            }
        }
        return false;
    }

    public void buyableLogic(Model model, UserIO userIO){
        purchaseField(model, userIO);
    }
    private void purchaseField(Model model, UserIO userIO){
        int currentPlayer = model.getCurrentTurn();
        int currenPosition = model.getPlayerCurrentTurn().getPosition();
        if (fieldAcceptTest(model)) {
            if (!model.gameBoard().isOwned(currenPosition)) {
                userIO.moveCar(model);
                String userInput = userIO.getUserButtonPressed("Vil du købe dette felt", "ja", "nej");
                switch (userInput) {
                    case "ja":
                        //the amount that needs to be added.
                        int price = model.gameBoard().getSpecificPrice(currenPosition, 0);
                        model.gameBoard().buyField(currenPosition, currentPlayer);
                        model.getPlayerCurrentTurn().addPlayerBalance(-price);

                        return;
                    case "nej":
                }
            }
        } else {
            payrent(currenPosition);
        }
    }
    private void payrent(int currentPosition){
        userIO.showMessage(model.getPlayerCurrentTurn().getName() + "! DU skal sgu betale rente kammerat!");
        int rentToPay = model.gameBoard().getFieldCurrentRent(currentPosition);
        model.getPlayerCurrentTurn().addPlayerBalance(-rentToPay);

    }

    private void purchaseHouse(){
    }



}
