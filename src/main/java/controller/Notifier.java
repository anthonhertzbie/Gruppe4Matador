package controller;

import model.Model;
import model.Player;

public abstract class Notifier {
    public abstract void notifyChanges(Model model);
    public abstract void startGame(Model model);
}
