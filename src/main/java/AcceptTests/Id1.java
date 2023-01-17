package AcceptTests;

import controller.Game_Controller;
import model.Model;
import view.View;
import view.ViewUserIO;

public class Id1 {
    private final Model model = new Model();

    public static void main(String[] args) {
        new Id1().run();
    }
    private void run(){
        // setup and start
        Game_Controller gameController = new Game_Controller(null, model);
        View view = new View(gameController);
        gameController.addNotifier(view);
        gameController.setUserIO(new ViewUserIO(view));
        view.setGui_start();
        gameController.ga

    }
}
