package controller;

import model.Model;

public abstract class Notifier {
    public abstract void notifyChanges(Model model);
    public abstract void updateMessage(Model updateMessages);
}