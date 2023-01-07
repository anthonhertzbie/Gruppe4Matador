package controller;

import model.Model;
import view.View;

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
        notifier.notifyChanges(model);
    }

    public void takeTurn(){
        model.turn();
    }


    public void game(){
        startGame();


        /*
        setPlayerNames(String playerNmes);
        createPlayerCars();
        createPlayerUI();
         */
    }

    public int setTotalPlayers(View view){
        int amount = Integer.parseInt(view.getTotalPlayers());
        model.setTotalPlayers(amount);
    }

    public void setPlayerName(String playerName, int playerAmount){
        model.setPlayerName(playerName, playerAmount);
    }


}
