package controller;


import model.Gameboard;
import model.Model;

public abstract class UserIO {

    public abstract String getUserButtonPressed(String message, String... userOptions);

    public abstract void showMessage(String Message);

    public abstract void showChanceCard(String description);


    public abstract void removePlayerLost(Model model);

    public abstract void setOwnerBorder(int index, int player);


    public abstract void setHouses(int fieldIndex, int houses, int currentRent, Gameboard gameboard);

    public abstract void updateView(Model model);
    public abstract void setRentPrice(int fieldIndex, String rentIncrease);
}
