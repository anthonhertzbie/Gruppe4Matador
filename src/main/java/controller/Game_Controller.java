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

}
