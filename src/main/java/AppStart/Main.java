package AppStart;

import controller.Game_Controller;
import controller.Notifier;
import controller.UserIO;
import gui_fields.*;
import gui_main.GUI;
import model.Model;
import view.View;
import view.ViewUserIO;

public class Main {




    public static void main(String[] args){

        Game_Controller gameController = new Game_Controller(null);
        View view = new View(gameController);
        gameController.addNotifier(view);
        gameController.setUserIO(new ViewUserIO(view));
        gameController.startGame();

    }

}