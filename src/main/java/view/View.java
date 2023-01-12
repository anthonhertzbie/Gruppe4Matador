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
import java.util.Objects;


public class View extends Notifier {
    // Loading relevant classes from GUI
    GUI gui;
    // Players
    GUI_Player[] gui_players;
    // Cars
    GUI_Car[] gui_cars;
    // Gameboard
    GUI_Field[] gui_fields = new GUI_Field[40];
    GUI_Ownable[] gui_ownables = new GUI_Street[40];
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

        gui_fields[0] = new GUI_Start("Start", "$$$$$", "Recieve much gold if you pass", Color.RED, Color.BLACK);
        gui_fields[1] = new GUI_Street("Rødovrevej",helper.getFieldData(2,3) + "kr","","",new Color(114,109,232),Color.black);
        gui_fields[2] = new GUI_Chance();gui_fields[2].setSubText("Chance card");
        gui_fields[3] = new GUI_Street("Hvidovrevej",helper.getFieldData(4,3) + "kr","d","",new Color(114,109,232),Color.black);
        gui_fields[4] = new GUI_Tax();gui_fields[4].setTitle("Tax!");gui_fields[4].setSubText("Pay up!");gui_fields[4].setDescription("Choose to either pay 4.000$ or 10% of your total assets.");
        gui_fields[5] = new GUI_Shipping("default","Helsingør","d","","",new Color(107,251,255),Color.black);
        gui_fields[6] = new GUI_Street("Roskildevej",helper.getFieldData(7,3) + "kr","d","20",new Color(150,90,0),Color.black);
        gui_fields[7] = new GUI_Chance();gui_fields[7].setSubText("Chance card");
        gui_fields[8] = new GUI_Street("Valby Langgade",helper.getFieldData(9,3) + "kr","d","20",new Color(150,90,0),Color.black);
        gui_fields[9] = new GUI_Street("Allégade",helper.getFieldData(10,3) + "kr","d","20",new Color(150,90,0),Color.black);
        gui_fields[10] = new GUI_Jail();gui_fields[10].setSubText("Visiting");
        gui_fields[11] = new GUI_Street("Frederiksberg Allé",helper.getFieldData(12,3) + "kr","d","20",Color.YELLOW,Color.black);
        gui_fields[12] = new GUI_Brewery("default",helper.getFieldData(13,3) + "kr","d","","20",Color.lightGray,Color.BLACK);
        gui_fields[13] = new GUI_Street("Bülowsvej",helper.getFieldData(14,3) + "kr","d","20",Color.YELLOW,Color.black);
        gui_fields[14] = new GUI_Street("t",helper.getFieldData(15,3) + "kr","d","20",Color.YELLOW,Color.black);
        gui_fields[15] = new GUI_Shipping("default",helper.getFieldData(16,3) + "kr","d","","20",new Color(107,251,255),Color.black);
        gui_fields[16] = new GUI_Street("t",helper.getFieldData(17,3) + "kr","d","20",Color.GRAY,Color.black);
        gui_fields[17] = new GUI_Chance();gui_fields[17].setSubText("Chance card");
        gui_fields[18] = new GUI_Street("t",helper.getFieldData(19,3) + "kr","d","20",Color.GRAY,Color.black);
        gui_fields[19] = new GUI_Street("t",helper.getFieldData(20,3) + "kr","d","20",Color.GRAY,Color.black);
        gui_fields[20] = new GUI_Refuge();gui_fields[20].setSubText("Free parking");
        gui_fields[21] = new GUI_Street("t",helper.getFieldData(22,3) + "kr","d","20",Color.RED,Color.black);
        gui_fields[22] = new GUI_Chance();gui_fields[22].setSubText("Chance card");
        gui_fields[23] = new GUI_Street("t",helper.getFieldData(24,3) + "kr","d","20",Color.RED,Color.black);
        gui_fields[24] = new GUI_Street("t",helper.getFieldData(25,3) + "kr","d","20",Color.RED,Color.black);
        gui_fields[25] = new GUI_Shipping("default",helper.getFieldData(26,3) + "kr","d","","20",new Color(107,251,255),Color.BLACK);
        gui_fields[26] = new GUI_Street("t",helper.getFieldData(27,3) + "kr","d","20",new Color(255,135,244),Color.black);
        gui_fields[27] = new GUI_Street("t",helper.getFieldData(28,3) + "kr","d","20",new Color(255,135,244),Color.black);
        gui_fields[28] = new GUI_Brewery("default",helper.getFieldData(29,3) + "kr","d","","20",Color.lightGray,Color.BLACK);
        gui_fields[29] = new GUI_Street("t",helper.getFieldData(30,3) + "kr","d","20",new Color(255,135,244),Color.black);
        gui_fields[30] = new GUI_Jail();gui_fields[30].setSubText("Go to jail!");
        gui_fields[31] = new GUI_Street("t",helper.getFieldData(32,3) + "kr","d","20",new Color(106, 176, 95),Color.black);
        gui_fields[32] = new GUI_Street("t",helper.getFieldData(33,3) + "kr","d","20",new Color(106, 176, 95),Color.black);
        gui_fields[33] = new GUI_Chance();gui_fields[33].setSubText("Chance card");
        gui_fields[34] = new GUI_Street("t",helper.getFieldData(35,3) + "kr","d","20",new Color(106, 176, 95),Color.black);
        gui_fields[35] = new GUI_Shipping("default",helper.getFieldData(36,3) + "kr","d","","20",new Color(107,251,255),Color.black);
        gui_fields[36] = new GUI_Chance();gui_fields[36].setSubText("Chance card");
        gui_fields[37] = new GUI_Street("t",helper.getFieldData(38,3) + "kr","d","20",new Color(171,52,175),Color.black);
        gui_fields[38] = new GUI_Tax();gui_fields[38].setTitle("Tax!");gui_fields[38].setSubText("Pay up!");gui_fields[38].setDescription("Pay  2000$");
        gui_fields[39] = new GUI_Street("t",helper.getFieldData(40,3) + "kr","d","20",new Color(171,52,175),Color.black);
        return gui_fields;
    }

    public void setBorder(int index,int player){
        int red = helper.getCarColour(player, 2);
        int green = helper.getCarColour(player, 3);
        int blue = helper.getCarColour(player, 4);
        GUI_Field f = gui.getFields()[index];
        if(f instanceof GUI_Ownable){
            GUI_Ownable o = (GUI_Ownable) f;
            o.setBorder(new Color(red, green, blue));
        }
    }
    public void startBorder(){
        for (int i = 0; i < gui_fields.length - 1; i++) {
            GUI_Field f = gui.getFields()[i];
            if(f instanceof GUI_Ownable){
                GUI_Ownable o = (GUI_Ownable) f;
                o.setBorder(new Color(0,0,0), new Color(255,255,255));
            }
        }
    }


    public void setGui_start(){
        gui = new GUI(gameBoardFields(), Color.ORANGE);
        startBorder();
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
        int red = helper.getCarColour(index, 2);
        int green = helper.getCarColour(index, 3);
        int blue = helper.getCarColour(index, 4);
        gui_cars[index].setPrimaryColor(new Color(red, green, blue));
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
