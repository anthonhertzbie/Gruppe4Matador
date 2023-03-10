package AcceptTests;

import controller.BuyableController;
import controller.Game_Controller;
import controller.UserIO;
import model.Model;
import view.View;
import view.ViewUserIO;

public class Id1 {
    private final Model model = new Model();
    private UserIO userIO;

    public static void main(String[] args) {
        new Id1().run();
    }
    private void run(){
        // setup and start
        Game_Controller gameController = new Game_Controller(model);
        View view = new View(gameController);
        userIO = new ViewUserIO(view);
        BuyableController buyableController = new BuyableController(model, userIO);
        gameController.addNotifier(view);
        gameController.setUserIO(userIO);
        view.setGui_start();



    }
}
