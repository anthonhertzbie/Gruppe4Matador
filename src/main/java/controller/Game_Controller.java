package controller;

import model.Model;
import view.View;

import java.util.ArrayList;

public class Game_Controller {
    private final Model model;
    private Notifier notifier;
    private final ArrayList<Notifier> notifiers = new ArrayList<>();

    private Fieldlogic_Controller fieldlogic;
    public Game_Controller(){
        this.model = new Model();
        this.fieldlogic = new Fieldlogic_Controller(model);
    }

    public void addNotifier(Notifier notifier){
        this.notifiers.add(notifier);
    }

    public void notifyEverything(){
        for (Notifier n:notifiers) {
            n.startGame(model);
        }
    }

    public void startGame(){

        model.setStartGUI(true);
        notifyEverything();
        model.setStartGUI(false);
        model.setNormalTurn(true);

        while (true){

            if (model.getPlayerCurrentTurn().getInJail() == false) {

                model.resetBooleans();
                diceRoll();
                checkForNormalTurn();
                fieldlogic.specialField();
                notifyEverything();
                model.changeTurn();

            }
            else{
                model.resetBooleans();
                diceRoll();
                checkForNormalTurn();
                fieldlogic.specialField();
                notifyEverything();
                model.changeTurn();
            }


        }
    }

    public void checkForNormalTurn(){
        model.resetBooleans();
        model.setBooleans();
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
