package controller;

import model.Model;
import view.View;

public class Game_Controller {
    private final Model model;
    private Notifier notifier;
    public Game_Controller(){
        this.model = new Model();
    }

    public void startGame(Notifier notifier){
        this.notifier = notifier;
        model.setStartGUI(true);
        notifier.startGame(model);
        model.setStartGUI(false);

        while (true){
            diceRoll();
            model.setNormalTurn(true);
            notifier.startGame(model);
            model.changeTurn();
        }


    }

    public void setTotalPlayerCount(String totalPlayerCount){
        model.setTotalPlayerCount(Integer.parseInt(totalPlayerCount));
    }

    public void setPlayerBalance(int balance){
    }

    public void diceRoll(){
        model.getCup().rollDices();
        model.setPlayerPosition(model.getCup().getSum());
    }

    public void setName(int index, String name){
        model.setPlayerName(index, name);
    }

}
