package controller;


import model.Model;

public abstract class UserIO {

    public abstract void waitForUserInput(String message);

    public abstract String getUserButtonPressed(String message, String ... userOptions);

    public abstract void showMessage(String Message);

    public abstract void moveCar(Model model);
    public abstract void showChanceCard(String description);

    public abstract void getUserSelection(String message, String... userOptions);

    public abstract void removePlayerLost(Model model);

    public abstract void setOwnerBorder(int index, int player);
    public abstract void setHouses(int fieldIndex, int houses);

    public abstract void updateView(Model model);


}
