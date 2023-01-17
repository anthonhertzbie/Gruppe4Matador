package AppStart;

import controller.Game_Controller;
import model.Model;
import view.View;
import view.ViewUserIO;

public class Main {




    public static void main(String[] args){

        Model model = new Model();
        Game_Controller gameController = new Game_Controller(model);
        View view = new View(gameController);
        gameController.addNotifier(view);
        gameController.setUserIO(new ViewUserIO(view));
        view.setupGUI(model);
        gameController.mainGameLoop();

    }

}