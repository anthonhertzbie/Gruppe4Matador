package controller;

import model.Player;
import MockUp.Model;

public abstract class Notifier {
    public abstract void notifyChanges(Model model);
    public abstract void startGame(Model model);
}
