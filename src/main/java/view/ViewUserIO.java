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
    public void moveCar(Model model) {
        view.moveCar(model);
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
    public void updateView(Model model) {
        view.updateView(model);
        view.moveCar(model);
    }
    public void setHouses(int fieldIndex, int houses){
        view.setHouses(fieldIndex, houses);
    }



}
