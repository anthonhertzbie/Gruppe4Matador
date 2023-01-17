package view;

import controller.Game_Controller;
import controller.Notifier;
import gui_fields.*;
import gui_main.GUI;
import model.*;

import java.awt.*;
import java.util.Objects;


public class View extends Notifier {
    // Loading relevant classes from GUI and other classes
    GUI gui;
    GUI_Player[] gui_players;
    GUI_Field[] gui_fields = new GUI_Field[40];
    Helper helper = new Helper();
    private Game_Controller gameController;
    public View(Game_Controller gameController){
        this.gameController = gameController;
    }


    public void setupGUI(Model model) {
        setGui_start();
        gameController.setTotalPlayerCount(setTotalPlayers());
        setGuiTotalPlayers(model);
        for (int i = 0; i < model.getTotalPlayerCount(); i++) {
            makePlayers(i, model);
        }
    }

    @Override
    public void notifyModel(Model model) {
        updateView(model);
    }
    public void removePlayerLost(Model model){
        gui_players[model.getCurrentTurn()].setName(model.getPlayerCurrentTurn().getName() + " has lost");
        gui_players[model.getCurrentTurn()].setBalance(model.getPlayerCurrentTurn().getPlayerBalance());
        gui_fields[model.getPlayerCurrentTurn().getPosition()].setCar(gui_players[model.getCurrentTurn()], false);
    }

    public void viewPlayers(Model model) {
        for (int i = 0; i < model.getTotalPlayerCount(); i++) {
            if (!model.hasPlayer(i)) continue;
            if (gui_players[i] == null) {
                GUI_Car gui_car = new GUI_Car();
                int red = helper.getCarColour(i, 2);
                int green = helper.getCarColour(i, 3);
                int blue = helper.getCarColour(i, 4);
                gui_car.setPrimaryColor(new Color(red, green, blue));
                gui_players[i] = new GUI_Player(model.getPlayerName(i), model.getPlayerBalance(i), gui_car);
                gui.addPlayer(gui_players[i]);
            }
            // Reset all fields
            for (int j = 0; j < 40; j++) {
                gui_fields[j].setCar(gui_players[i], false);
            }
            if (!model.getPlayerByIndex(i).getHasLost()) {
                int pos = model.getPlayerByIndex(i).getPosition();
                gui_fields[pos].setCar(gui_players[i], true);
            }
        }
    }

    public void updateView(Model model) {

        viewPlayers(model);
        if (model.isGameIsOver())
        {
            gui.close();
        }
        setDice(model.getCup());
        updateAccounts(model);
    }

    public void updateAccounts(Model model){
        for(int i  = 0; i < model.getTotalPlayerCount(); i++){
            if (!model.hasPlayer(i)) continue;
            gui_players[i].setBalance(model.getPlayerBalance(i));
        }
    }



    public void setHouses(int fieldIndex, int numberOfHouses, int currentRent, Gameboard gameboard){

        GUI_Field f = gui.getFields()[fieldIndex];
        if(f instanceof GUI_Ownable){
            GUI_Street s = (GUI_Street) f;
            if (gameboard.getFieldCurrentRent(fieldIndex) <= gameboard.getSpecificPrice(fieldIndex, 6)){
                System.out.println("Im in house mode");
                s.setHotel(false);
                s.setHouses(numberOfHouses);
                s.setSubText("leje: " + currentRent);
                s.setRent(Integer.toString(currentRent));
            }
            else if(gameboard.getFieldCurrentRent(fieldIndex) == gameboard.getSpecificPrice(fieldIndex, 7)) {
                System.out.println(gameboard.getFieldCurrentRent(fieldIndex));
                System.out.println("Im in hotel mode");
                s.setHotel(true);
                s.setSubText("leje: " + currentRent);
                s.setRent(Integer.toString(currentRent));
            }
        }
    }




    public GUI_Field[] gameBoardFields(){

        gui_fields[0] = new GUI_Start(helper.getFieldData(1,0), "$$$$$", "Recieve much gold if you pass", Color.RED, Color.BLACK);
        gui_fields[1] = new GUI_Street(helper.getFieldData(2,0),"køb: " + helper.getFieldData(2,3) + "kr","","",new Color(114,109,232),Color.black);
        gui_fields[2] = new GUI_Chance();gui_fields[2].setSubText("Chance card");
        gui_fields[3] = new GUI_Street(helper.getFieldData(4,0),"køb: " + helper.getFieldData(4,3) + "kr","","",new Color(114,109,232),Color.black);
        gui_fields[4] = new GUI_Tax();gui_fields[4].setTitle("Tax!");gui_fields[4].setSubText("Pay up!");gui_fields[4].setDescription("Choose to either pay 4.000$ or 10% of your total assets.");
        gui_fields[5] = new GUI_Shipping("default",helper.getFieldData(6,3) + "kr","Ferry","","",new Color(107,251,255),Color.black);
        gui_fields[6] = new GUI_Street(helper.getFieldData(7,0),"køb: " + helper.getFieldData(7,3) + "kr","","",new Color(120,80,50),Color.black);
        gui_fields[7] = new GUI_Chance();gui_fields[7].setSubText("Chance card");
        gui_fields[8] = new GUI_Street(helper.getFieldData(9,0),"køb: " + helper.getFieldData(9,3) + "kr","","",new Color(120,80,50),Color.black);
        gui_fields[9] = new GUI_Street(helper.getFieldData(10,0),"køb: " + helper.getFieldData(10,3) + "kr","","",new Color(120,80,50),Color.black);
        gui_fields[10] = new GUI_Jail();gui_fields[10].setSubText("Visiting");
        gui_fields[11] = new GUI_Street(helper.getFieldData(12,0),"køb: " + helper.getFieldData(12,3) + "kr","","",Color.YELLOW,Color.black);
        gui_fields[12] = new GUI_Brewery("default",helper.getFieldData(13,3) + "kr","Brewery","","",Color.lightGray,Color.BLACK);
        gui_fields[13] = new GUI_Street(helper.getFieldData(14,0),"køb: " + helper.getFieldData(14,3) + "kr","","",Color.YELLOW,Color.black);
        gui_fields[14] = new GUI_Street(helper.getFieldData(15,0),"køb: " + helper.getFieldData(15,3) + "kr","","",Color.YELLOW,Color.black);
        gui_fields[15] = new GUI_Shipping("default",helper.getFieldData(16,3) + "kr","Ferry","","",new Color(107,251,255),Color.black);
        gui_fields[16] = new GUI_Street(helper.getFieldData(17,0),"køb: " + helper.getFieldData(17,3) + "kr","","",Color.GRAY,Color.black);
        gui_fields[17] = new GUI_Chance();gui_fields[17].setSubText("Chance card");
        gui_fields[18] = new GUI_Street(helper.getFieldData(19,0),"køb: " + helper.getFieldData(19,3) + "kr","","",Color.GRAY,Color.black);
        gui_fields[19] = new GUI_Street(helper.getFieldData(20,0),"køb: " + helper.getFieldData(20,3) + "kr","","",Color.GRAY,Color.black);
        gui_fields[20] = new GUI_Refuge();gui_fields[20].setSubText("Free parking");
        gui_fields[21] = new GUI_Street(helper.getFieldData(22,0),"køb: " + helper.getFieldData(22,3) + "kr","","",new Color(255,43,43),Color.black);
        gui_fields[22] = new GUI_Chance();gui_fields[22].setSubText("Chance card");
        gui_fields[23] = new GUI_Street(helper.getFieldData(24,0),"køb: " + helper.getFieldData(24,3) + "kr","","",new Color(255,43,43),Color.black);
        gui_fields[24] = new GUI_Street(helper.getFieldData(25,0),"køb: " + helper.getFieldData(25,3) + "kr","","",new Color(255,43,43),Color.black);
        gui_fields[25] = new GUI_Shipping("default",helper.getFieldData(26,3) + "kr","Ferry","","",new Color(107,251,255),Color.BLACK);
        gui_fields[26] = new GUI_Street(helper.getFieldData(27,0),"køb: " + helper.getFieldData(27,3) + "kr","","",new Color(255,135,244),Color.black);
        gui_fields[27] = new GUI_Street(helper.getFieldData(28,0),"køb: " + helper.getFieldData(28,3) + "kr","","",new Color(255,135,244),Color.black);
        gui_fields[28] = new GUI_Brewery("default",helper.getFieldData(29,3) + "kr","Brewery","","",Color.lightGray,Color.BLACK);
        gui_fields[29] = new GUI_Street(helper.getFieldData(30,0),"køb: " + helper.getFieldData(30,3) + "kr","","",new Color(255,135,244),Color.black);
        gui_fields[30] = new GUI_Jail();gui_fields[30].setSubText("Go to jail!");
        gui_fields[31] = new GUI_Street(helper.getFieldData(32,0),"køb: " + helper.getFieldData(32,3) + "kr","","",new Color(106, 176, 95),Color.black);
        gui_fields[32] = new GUI_Street(helper.getFieldData(33,0),"køb: " + helper.getFieldData(33,3) + "kr","","",new Color(106, 176, 95),Color.black);
        gui_fields[33] = new GUI_Chance();gui_fields[33].setSubText("Chance card");
        gui_fields[34] = new GUI_Street(helper.getFieldData(35,0),"køb: " + helper.getFieldData(35,3) + "kr","","",new Color(106, 176, 95),Color.black);
        gui_fields[35] = new GUI_Shipping("default",helper.getFieldData(36,3) + "kr","Ferry","","",new Color(107,251,255),Color.black);
        gui_fields[36] = new GUI_Chance();gui_fields[36].setSubText("Chance card");
        gui_fields[37] = new GUI_Street(helper.getFieldData(38,0),"køb: " + helper.getFieldData(38,3) + "kr","","",new Color(171,52,175),Color.black);
        gui_fields[38] = new GUI_Tax();gui_fields[38].setTitle("Tax!");gui_fields[38].setSubText("Pay up!");gui_fields[38].setDescription("Pay  2000$");
        gui_fields[39] = new GUI_Street(helper.getFieldData(40,0),"køb: " + helper.getFieldData(40,3) + "kr","","",new Color(171,52,175),Color.black);
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
        for (int i = 0; i < gui_fields.length; i++) {
            GUI_Field f = gui.getFields()[i];
            if(f instanceof GUI_Ownable){
                GUI_Ownable o = (GUI_Ownable) f;
                o.setBorder(new Color(0,0,0), new Color(255,255,255));
            }
        }
    }
    public void setRentPrice(int fieldIndex, String rentIncrease){
        gui_fields[fieldIndex].setSubText(rentIncrease);
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
        gameController.setName(index, playerName);
    }





    public void setGuiTotalPlayers(Model model){
        // Sets the number of cars and players participating in the game.
        gui_players = new GUI_Player[model.getTotalPlayerCount()];
    }


    public String setTotalPlayers(){
        return gui.getUserSelection("Choose amount of players : ", "3", "4", "5", "6");
    }
}
