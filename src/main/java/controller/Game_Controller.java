package controller;

import model.Model;

public class Game_Controller {
    private Model model;
    private final Notifier notifier;

    public Game_Controller(){
        this.model = new Model();
        this.notifier = notifier;
    }

    public void movePlayer(){
        model.getPlayer();
        model.setAccountBalance(50);
        notifier.notifyChanges(model);
    }

}
