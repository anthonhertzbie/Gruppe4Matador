package view;

import controller.UserIO;

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
    public void moveCar(int oldPosition, int newPosition, int currentTurn) {
        view.moveCar(oldPosition, newPosition, currentTurn);
    }


    @Override
    public void getUserSelection(String message, String... userOptions) {
        view.gui.getUserSelection(message, userOptions);
    }


}
