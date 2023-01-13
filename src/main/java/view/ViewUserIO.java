package view;

import controller.UserIO;
import model.Model;

public class ViewUserIO extends UserIO {
    private final View view;

    public ViewUserIO(View view) {
        this.view = view;
    }

    @Override
    public void waitForUserInput(String message) {
        view.gui.showMessage(message);
    }

    @Override
    public String getUserButtonPressed(String message, String ... userOptions) {
        return view.gui.getUserButtonPressed(message, userOptions);
    }

    @Override
    public void showMessage(String message) {
        view.gui.showMessage(message);
    }



    @Override
    public void getUserSelection(String message, String... userOptions) {
        view.gui.getUserSelection(message, userOptions);
    }

    @Override
    public void removePlayerLost(Model model) {
        view.removePlayerLost(model);
    }

    public void showChanceCard(String description){
        view.gui.displayChanceCard(description);
    }

    public void setOwnerBorder(int index, int player){
        view.setBorder(index, player);
    }

    @Override
    public void viewPlayers(Model model) {
        view.viewPlayers(model);
    }

    public void setHouses(int index, int houses) {
        view.setHouses(index, houses);
    }

    @Override
    public void updateView(Model model) {
        view.updateView(model);
    }


    @Override
    public int getUserInteger(String message, int min, int max) {
        return view.gui.getUserInteger(message,min,max);
    }




}
