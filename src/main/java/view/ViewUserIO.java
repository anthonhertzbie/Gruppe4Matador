package view;

import controller.UserIO;
import model.Gameboard;
import model.Model;

public class ViewUserIO extends UserIO {
    private final View view;

    public ViewUserIO(View view) {
        this.view = view;
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
    public void moveCar(Model model) {

    }

    public void setRentPrice(int fieldIndex, String rentIncrease){
        view.setRentPrice(fieldIndex, rentIncrease);
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
    public void updateView(Model model) {
        view.updateView(model);
    }


    @Override
    public void setHouses(int fieldIndex, int houses, int currentRent, Gameboard gameboard){
        view.setHouses(fieldIndex, houses, currentRent, gameboard);
    }



}
