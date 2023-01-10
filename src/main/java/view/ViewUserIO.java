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
        return view.gui.getUserSelection(message, userOptions);
    }


}
