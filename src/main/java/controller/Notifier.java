package controller;

import model.Model;

public abstract class Notifier {
    public abstract void startGame(Model model);
    public abstract void updateMessage(Model updateMessages);
}