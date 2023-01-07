package controller;

import model.Model;

public class Game_Controller {
    private final Model model;
    private final Notifier notifier;

    public Game_Controller(Notifier notifier){
        this.notifier = notifier;
        this.model = new Model();
    }

    public void movePlayer(){
        model.getPlayer();
        model.setAccountBalance(50);
        notifier.notifyChanges(model);
    }

    public void startGame(){
        model.setTextInput("Hello");
        model.addPlayer();
        notifier.notifyChanges(model);

        model.setTextInput("Test");
        model.setShowMessages("Test");
    }

    public void takeTurn(){
        model.turn();
    }

    public void game(){
        startGame();
        setPlayerAmount();
        setPlayerNames(String playerNmes);
        createPlayerCars();
        createPlayerUI();
    }

}
