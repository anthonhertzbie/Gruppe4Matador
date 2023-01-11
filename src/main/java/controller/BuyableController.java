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

    }
    private void purchaseField(Model model, UserIO userIO){
        Player currentPlayer = model.getPlayerCurrentTurn();
        int currenPosition = currentPlayer.getPosition();
        if(model.gameBoard().isOwned(1, currenPosition)){

        }
    }
    private void purchaseHouse(){

    }



}
