package controller;


import model.Gameboard;
import model.Model;

public abstract class UserIO {

    public abstract void waitForUserInput(String message);

    public abstract String getUserButtonPressed(String message, String... userOptions);

    public abstract void showMessage(String Message);

    public abstract void moveCar(Model model);

    public abstract void showChanceCard(String description);

    public abstract void getUserSelection(String message, String... userOptions);

    public abstract void removePlayerLost(Model model);

    public abstract void setOwnerBorder(int index, int player);

    public abstract void viewPlayers(Model model);

    public abstract void setHouses(int fieldIndex, int houses, int currentRent, Gameboard gameboard);

    public abstract void updateView(Model model);
    public abstract void setRentPrice(int fieldIndex, String rentIncrease);
    public abstract int getUserInteger(String message, int min, int max);
}
