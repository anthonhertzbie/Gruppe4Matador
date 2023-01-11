package controller;

import model.Model;
import model.Player;

public class BuyableController {
    private Model model;
    private UserIO userIO;

    public BuyableController(Model model, UserIO userIO){
        this.model = model;
        this.userIO = userIO;
    }
    public void buyableLogic(Model model, UserIO userIO){
        purchaseField(model, userIO);
    }
    private void purchaseField(Model model, UserIO userIO){
        int currentPlayer = model.getCurrentTurn();
        int currenPosition = model.getPlayerCurrentTurn().getPosition();
        int previousPosition = model.getPlayerCurrentTurn().getPreviousPosition();
        if(!model.gameBoard().isOwned(currenPosition, currentPlayer)){
            userIO.moveCar(previousPosition, currenPosition, currentPlayer);
            String userInput = userIO.getUserButtonPressed("Vil du købe dette felt", "ja", "nej");
            switch (userInput){
                case "ja":
                    //the amount that needs to be added.
                    int price = model.gameBoard().getSpecificPrice(currenPosition, 0);
                    model.gameBoard().buyField(currenPosition, currentPlayer);
                    model.getPlayerCurrentTurn().addPlayerBalance(-price);

                    return;
                case "nej":
            }
        }
    }
    private void purchaseHouse(){

    }



}
