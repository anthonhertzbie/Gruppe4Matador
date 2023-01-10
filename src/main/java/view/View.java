package view;

import controller.Game_Controller;
import controller.Notifier;
import gui_fields.*;
import gui_main.GUI;
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
        if (model.getPlayerCurrentTurn().getHasLost()) {
            gui.addPlayer(gui_players[model.getCurrentTurn()]);
        }
    }

    public void updateView(Model model) {
        if (model.getNormalTurn()) {
            System.out.println("Normal view");
            setDice(model);
            moveCar(model);
            updateAccounts(model);
        } else if (model.isPrison()) {
            System.out.println("Prison view");
            prison(model);
            moveCar(model);
            updateAccounts(model);
        } else if (model.isChanceCard()) {
            System.out.println("Chance card view");
            setDice(model);
            showChancecard(model);
            if (model.getDeck().getFirstCard().getIndex() == 43 || model.getDeck().getFirstCard().getIndex() == 44) {
                gui_fields[model.getPlayerCurrentTurn().getPreviousPosition()].setCar(gui_players[model.getCurrentTurn()], false);
                gui_fields[model.getPlayerCurrentTurn().getPreviousPositionChanceCard()].setCar(gui_players[model.getCurrentTurn()], true);
                gui.showMessage("You have been put in jail :(");
                gui_fields[model.getPlayerCurrentTurn().getPreviousPositionChanceCard()].setCar(gui_players[model.getCurrentTurn()], false);
            }

            moveCar(model);
            updateAccounts(model);
        } else if (model.isBrewery()) {
            setDice(model);
            moveCar(model);
            updateAccounts(model);
        } else if (model.isTax()) {
            setDice(model);
            moveCar(model);
            showTax(model);
            updateAccounts(model);
        } else if (model.isShipping()) {
            setDice(model);
            moveCar(model);
            updateAccounts(model);
        } else if (model.isParking()) {
            setDice(model);
            moveCar(model);
            updateAccounts(model);
        }
    }

    public void chanceCardGoToPrison(Model model){

    }

    public void prison(Model model){
        Player currentPlayer = model.getPlayerCurrentTurn();
        System.out.println("Am i running?");
        if (currentPlayer.getInJail() && currentPlayer.getInJailTurn() == 0){
            // Doing a little trickery here to circumvent the fact that the model knows you are in jail before the view does.
            gui.showMessage(currentPlayer.getName() + " Press OK to roll the dices : ");
            gui_fields[model.getPlayerCurrentTurn().getPreviousPosition()].setCar(gui_players[model.getCurrentTurn()], false);
            gui_fields[30].setCar(gui_players[model.getCurrentTurn()], true);
            gui.showMessage("You have been put in jail!");
            gui_fields[30].setCar(gui_players[model.getCurrentTurn()], false);
        }
        else if (currentPlayer.getInJail() && currentPlayer.getInJailTurn() < 2){
            String options[] = {"Pay 1000$ to get out", "Roll the dices", "Use get outta jail card"};
            String option;

            if (currentPlayer.getHasJailCard()){
                option = gui.getUserButtonPressed(currentPlayer.getName() + " you are still in jail.", "Pay 1000$ to get out", "Roll the dices", "Use get outta jail card");
            } else{
                option = gui.getUserButtonPressed(currentPlayer.getName() + " you are still in jail.", "Pay 1000$ to get out", "Roll the dices");}


            if (option.equals(options[0])){
                gui.showMessage("You have paid 1000$ to get out. ");
                gameController.addPlayerBalance(-1000);
                gameController.setJailFalseCurrentTurn();
                gameController.editTurn(-1);
            }else if (option.equals(options[1])){
                gameController.diceRoll();
                if (model.getCup().getDice1() == model.getCup().getDice2()){
                    gui.showMessage("You are free!");
                    gameController.setJailFalseCurrentTurn();
                    gameController.editTurn(-1);
                }
            }else if (option.equals(options[2])){
                gui.showMessage("You used teh good card :(");
                gameController.setJailFalseCurrentTurn();
                gameController.editTurn(-1);
            }
        }
        else if(currentPlayer.getInJail() && currentPlayer.getInJailTurn() == 2){
            gui.showMessage("You're free... For now...");
            gameController.setJailFalseCurrentTurn();
            gameController.gameTurn();
        }
    }


    private void showChancecard(Model model){
        gui.setChanceCard(model.getDeck().drawCard().toString());
        gui.displayChanceCard();
    }

    public void showTax(Model model) {
        Player currentPlayer = model.getPlayerCurrentTurn();
        if (currentPlayer.getPosition() == 4) {
            String[] options = {"10%", "4000$"};
            String option = gui.getUserButtonPressed("Income tax: Pay 10% of your total assets or 4000$", "10%", "4000$");
            if (option.equals(options[0])){
                int tempBalance = (int) Math.round(currentPlayer.getValueOfAllAssets() * 0.9);
                tempBalance = tempBalance - (tempBalance % 100);
                currentPlayer.addPlayerBalance(tempBalance - currentPlayer.getValueOfAllAssets());
            }
            else {
                currentPlayer.addPlayerBalance(-4000);
                gui.showMessage("You have paid 4000$");
            }
        }else { gui.showMessage("Special-tax, press OK to pay 2000$");
        }
    }

    public void updateAccounts(Model model){
        for(int i  = 0; i < model.getTotalPlayerCount(); i++){
            gui_players[i].setBalance(model.getPlayerBalance(i));
        }
    }

    public void moveCar(Model model){
        gui_fields[model.getPlayerCurrentTurn().getPreviousPosition()].setCar(gui_players[model.getCurrentTurn()], false);
        gui_fields[model.getPlayerCurrentTurn().getPosition()].setCar(gui_players[model.getCurrentTurn()], true);
    }

    public GUI_Field[] gameBoardFields(){
        helper.getFieldData(0,0);

        gui_fields[0] = new GUI_Start("Start", "$$$$$", "Recieve much gold if you pass", Color.RED, Color.BLACK);
        gui_fields[1] = new GUI_Street("Rødovrevej","","","2000",Color.BLUE,Color.black);
        gui_fields[2] = new GUI_Chance();gui_fields[2].setSubText("Chance card");
        gui_fields[3] = new GUI_Street("Hvidovrevej","st","d","20",Color.BLUE,Color.black);
        gui_fields[4] = new GUI_Tax();gui_fields[4].setTitle("Tax!");gui_fields[4].setSubText("Pay up!");gui_fields[4].setDescription("Choose to either pay 4.000$ or 10% of your total assets.");
        gui_fields[5] = new GUI_Shipping("default","Helsingør","d","","20",Color.BLUE,Color.black);
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

    public void setDice(Model model){
        gui.showMessage(model.getPlayerCurrentTurn().getName() + " press ok to roll the dices : ");
        gui.setDice(model.getCup().getDice1(), model.getCup().getDice2());

    }

    public void makePlayers(int index, Model model){
        int player = index + 1;
        String playerName = gui.getUserString("Enter name of player " + player + " : ");
        Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.GRAY, Color.magenta};
        gameController.setName(index, playerName);
        gui_cars[index] = new GUI_Car();
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
