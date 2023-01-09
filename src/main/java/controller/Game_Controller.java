package controller;

import model.Model;

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
        gameTurn();


    }

    public void gameTurn(){


        while (true){

            if (model.getPlayerCurrentTurn().getInJail() == false) {

                System.out.println("Controller How the fuq?" + " " + model.getPlayerCurrentTurn().getInJailTurn() + " more dfq? " + model.getPlayerCurrentTurn().getInJailTurn());
                model.resetBooleans();
                diceRoll();
                model.setPlayerPosition(model.getCup().getSum());
                notifierWithLogic();
            }
            else{
                notifierWithLogic();
            }
        }
    }




    public void notifierWithLogic(){
        BooleanReset();
        fieldlogic.specialField();
        notifyEverything();
        model.changeTurn();
    }


    public void setJailFalseCurrentTurn(){
        model.getPlayerCurrentTurn().setInJail(false);
    }

    public void BooleanReset(){
        model.resetBooleans();
        model.setBooleans();
    }


    public void editTurn(int number){
        model.addCurrentTurn(number);
    }

    public void setTotalPlayerCount(String totalPlayerCount){
        model.setTotalPlayerCount(Integer.parseInt(totalPlayerCount));
    }

    public void addPlayerBalance(int balance){
        model.getPlayerCurrentTurn().addPlayerBalance(balance);
    }

    public void diceRoll(){
        model.getCup().rollDices();
    }

    public void setName(int index, String name){
        model.setPlayerName(index, name);
    }

}
