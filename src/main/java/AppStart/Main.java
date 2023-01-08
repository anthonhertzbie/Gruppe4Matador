package AppStart;

import controller.Game_Controller;
import controller.Notifier;
import gui_fields.*;
import gui_main.GUI;
import model.Model;
import view.View;

public class Main {




    public static void main(String[] args){
        Game_Controller gameController = new Game_Controller();
        gameController.startGame(new View(gameController));

    }
}