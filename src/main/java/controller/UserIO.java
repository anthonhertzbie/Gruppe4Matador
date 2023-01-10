package controller;

import model.Model;

public abstract class UserIO {

    public abstract void waitForUserInput(String message);

    public abstract String getUserButtonPressed(String message, String ... userOptions);

    public abstract void showMessage(String Message);


}
