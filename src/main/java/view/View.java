package view;

import controller.Game_Controller;
import controller.Notifier;
import gui_fields.*;
import gui_main.GUI;
import model.Cup;
import model.Helper;
import model.Model;
import model.Player;

import java.awt.*;


public class View extends Notifier {
    // Loading relevant classes from GUI
    GUI gui;
    // Players
    GUI_Player[] gui_players;
    // Cars
    GUI_Car[] gui_cars;
    // Gameboard
    GUI_Field[] gui_fields = new GUI_Field[40];
    GUI_Street[] gui_streets = new GUI_Street[5];
    GUI_Jail gui_jail;
    GUI_Chance gui_chance;
    GUI_Tax gui_taxes;
    GUI_Start gui_start;
    GUI_Brewery gui_brewery;
    GUI_Board gui_board;
    Helper helper = new Helper();
    private Notifier notifier;
    private Game_Controller gameController;
    public View(Game_Controller gameController){
        this.gameController = gameController;
    }



    @Override
    public void startGame(Model model) {
        if (model.getStartGUI()) {

            setGui_start();
            gameController.setTotalPlayerCount(setTotalPlayers());
            setGuiTotalPlayers(model);
            for (int i = 0; i < model.getTotalPlayerCount(); i++) {
                makePlayers(i, model);
            }
        }
        updateView(model);
    }
    public void removePlayerLost(Model model){
        gui_players[model.getCurrentTurn()].setName(model.getPlayerCurrentTurn().getName() + " has lost");
        gui_players[model.getCurrentTurn()].setBalance(model.getPlayerCurrentTurn().getPlayerBalance());
        gui_fields[model.getPlayerCurrentTurn().getPosition()].setCar(gui_players[model.getCurrentTurn()], false);
    }

    public void updateView(Model model) {
        if (model.isGameIsOver())
        {
            gui.close();
        }
        Player currentPlayer = model.getPlayerCurrentTurn();
        setDice(model.getCup());
        moveCar(model);
        updateAccounts(model);
    }

    public void updateAccounts(Model model){
        for(int i  = 0; i < model.getTotalPlayerCount(); i++){
            gui_players[i].setBalance(model.getPlayerBalance(i));
        }
    }

    public void moveCar(Model model){
        for(int i = 0; i < 40; i++){
            if (gui_fields[i].hasCar(gui_players[model.getCurrentTurn()])){
                gui_fields[i].setCar(gui_players[model.getCurrentTurn()], false);
            }
        }

        gui_fields[model.getPlayerCurrentTurn().getPosition()].setCar(gui_players[model.getCurrentTurn()], true);



        /*
        gui_fields[oldPosition].setCar(gui_players[currentTurn], false);
        gui_fields[newPosition].setCar(gui_players[currentTurn], true);


        /*
        gui_fields[oldPosition].setCar(gui_players[currentTurn], false);
        gui_fields[newPosition].setCar(gui_players[currentTurn], true);

         */
    }

    public GUI_Field[] gameBoardFields(){
        helper.getFieldData(0,0);

        gui_fields[0] = new GUI_Start("Start", "$$$$$", "Recieve much gold if you pass", Color.RED, Color.BLACK);
        gui_fields[1] = new GUI_Street("Rødovrevej","Buy 1200","","",Color.BLUE,Color.black);
        gui_fields[2] = new GUI_Chance();gui_fields[2].setSubText("Chance card");
        gui_fields[3] = new GUI_Street("Hvidovrevej","Buy 1200","d","",Color.BLUE,Color.black);
        gui_fields[4] = new GUI_Tax();gui_fields[4].setTitle("Tax!");gui_fields[4].setSubText("Pay up!");gui_fields[4].setDescription("Choose to either pay 4.000$ or 10% of your total assets.");
        gui_fields[5] = new GUI_Shipping("default","Helsingør","d","","",Color.BLUE,Color.black);
        gui_fields[6] = new GUI_Street("Roskildevej","st","d","20",Color.ORANGE,Color.black);
        gui_fields[7] = new GUI_Chance();gui_fields[7].setSubText("Chance card");
        gui_fields[8] = new GUI_Street("Valby Langgade","st","d","20",Color.ORANGE,Color.black);
        gui_fields[9] = new GUI_Street("Allégade","st","d","20",Color.ORANGE,Color.black);
        gui_fields[10] = new GUI_Jail();gui_fields[10].setSubText("Visiting");
        gui_fields[11] = new GUI_Street("Frederiksberg Allé","st","d","20",Color.YELLOW,Color.black);
        gui_fields[12] = new GUI_Brewery("default","Tuborg Squash","d","","20",Color.ORANGE,Color.black);
        gui_fields[13] = new GUI_Street("Bülowsvej","st","d","20",Color.YELLOW,Color.black);
        gui_fields[14] = new GUI_Street("t","st","d","20",Color.YELLOW,Color.black);
        gui_fields[15] = new GUI_Shipping("default","st","d","","20",Color.RED,Color.black);
        gui_fields[16] = new GUI_Street("t","st","d","20",Color.GRAY,Color.black);
        gui_fields[17] = new GUI_Chance();gui_fields[17].setSubText("Chance card");
        gui_fields[18] = new GUI_Street("t","st","d","20",Color.GRAY,Color.black);
        gui_fields[19] = new GUI_Street("t","st","d","20",Color.GRAY,Color.black);
        gui_fields[20] = new GUI_Refuge();gui_fields[20].setSubText("Free parking");
        gui_fields[21] = new GUI_Street("t","st","d","20",Color.RED,Color.black);
        gui_fields[22] = new GUI_Chance();gui_fields[22].setSubText("Chance card");
        gui_fields[23] = new GUI_Street("t","st","d","20",Color.RED,Color.black);
        gui_fields[24] = new GUI_Street("t","st","d","20",Color.RED,Color.black);
        gui_fields[25] = new GUI_Shipping("default","st","d","","20",Color.BLUE,Color.BLACK);
        gui_fields[26] = new GUI_Street("t","st","d","20",Color.LIGHT_GRAY,Color.black);
        gui_fields[27] = new GUI_Street("t","st","d","20",Color.LIGHT_GRAY,Color.black);
        gui_fields[28] = new GUI_Brewery("default","st","d","","20",Color.RED,Color.WHITE);
        gui_fields[29] = new GUI_Street("t","st","d","20",Color.LIGHT_GRAY,Color.black);
        gui_fields[30] = new GUI_Jail();gui_fields[30].setSubText("Go to jail!");
        gui_fields[31] = new GUI_Street("t","st","d","20",Color.YELLOW,Color.black);
        gui_fields[32] = new GUI_Street("t","st","d","20",Color.YELLOW,Color.black);
        gui_fields[33] = new GUI_Chance();gui_fields[33].setSubText("Chance card");
        gui_fields[34] = new GUI_Street("t","st","d","20",Color.YELLOW,Color.black);
        gui_fields[35] = new GUI_Shipping("default","st","d","","20",Color.BLUE,Color.black);
        gui_fields[36] = new GUI_Chance();gui_fields[36].setSubText("Chance card");
        gui_fields[37] = new GUI_Street("t","st","d","20",Color.magenta,Color.black);
        gui_fields[38] = new GUI_Tax();gui_fields[38].setTitle("Tax!");gui_fields[38].setSubText("Pay up!");gui_fields[38].setDescription("Pay  2000$");
        gui_fields[39] = new GUI_Street("t","st","d","20",Color.magenta,Color.black);

        return gui_fields;
    }
    public void setGui_start(){
        gui = new GUI(gameBoardFields(), Color.ORANGE);
    }

    public void setDice(Cup cup){
        gui.setDice(cup.getDice1(), cup.getDice2());
    }

    public void makePlayers(int index, Model model){
        int player = index + 1;
        String playerName = gui.getUserString("Enter name of player " + player + " : ");
        Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.GRAY, Color.magenta};
        gameController.setName(index, playerName);
        gui_cars[index] = new GUI_Car();
        switch(index) {
            case 0:
                gui_cars[index].setPrimaryColor(Color.blue);
                return;
            case 1:
                gui_cars[index].setPrimaryColor(Color.blue);
                return;
            case 2:
                gui_cars[index].setPrimaryColor(Color.blue);
                return;
            case 3:
                gui_cars[index].setPrimaryColor(Color.blue);
                return;
            case 4:
                gui_cars[index].setPrimaryColor(Color.blue);
                return;
            case 5:
                gui_cars[index].setPrimaryColor(Color.blue);

        }
        gui_players[index] = new GUI_Player(playerName, model.getPlayerBalance(index), gui_cars[index]);
        gui.addPlayer(gui_players[index]);
        gui_fields[0].setCar(gui_players[index], true);
    }



    public void setGui_close(){
        gui.close();
    }


    public void setGuiTotalPlayers(Model model){
        // Sets the number of cars and players participating in the game.
        gui_players = new GUI_Player[model.getTotalPlayerCount()];
        gui_cars = new GUI_Car[model.getTotalPlayerCount()];
    }


    public String setTotalPlayers(){
        return gui.getUserSelection("Choose amount of players : ", "3", "4", "5", "6");
    }
}
