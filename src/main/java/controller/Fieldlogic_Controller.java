package controller;

import model.Model;
import model.Player;

public class Fieldlogic_Controller {
    private Model model;
    private UserIO userIO;

    public Fieldlogic_Controller(Model model, UserIO userIO){
        this.model = model;
        this.userIO = userIO;
    }

    public void specialField(){
        if(model.isChanceCard()){
            chanceCardField(model);
        }else if (model.isPrison()){
            jailField(model);
        }else if(model.isTax()){
            taxFields(model);
        }
    }

    public void jailField(Model model){
        Player currentPlayer = model.getPlayerCurrentTurn();
        int position = currentPlayer.getPosition();
        int jailTurn = currentPlayer.getInJailTurn();
        String output;
        // the case when you've sat in prison for 2 rounds
        if (position == 10 && currentPlayer.isInJail() && jailTurn < 2) {
            if (currentPlayer.getHasJailCard()) {
                output = userIO.getUserButtonPressed(currentPlayer.getName() + " you are still in jail.", "Pay 1000$ to get out", "Roll the dices", "Use get outta jail card");
            } else {
                output = userIO.getUserButtonPressed(currentPlayer.getName() + " you are still in jail.", "Pay 1000$ to get out", "Roll the dices");
            }

            switch (output) {
                case "Pay 1000$ to get out":
                    currentPlayer.setInJail(false);
                    currentPlayer.addPlayerBalance(-1000);
                    model.addCurrentTurn(-1);
                    break;
                case "Roll the dices":
                    model.getCup().rollDices();
                    if(model.getCup().getDice1() == model.getCup().getDice2()) {
                        userIO.showMessage("Tillykke du er fri");
                        currentPlayer.setInJail(false);
                        currentPlayer.setInJailTurn(0);
                    } else{
                        userIO.showMessage("too bad... du slog ikke to ens ");
                    }
                    break;
                case "Use get outta jail card":
                    currentPlayer.setInJail(false);
                    model.addCurrentTurn(-1);
                    break;
            }
            currentPlayer.setInJailTurn(currentPlayer.getInJailTurn() + 1);
        } else if (position == 10 && jailTurn == 2){
            currentPlayer.setInJail(false);
            currentPlayer.setInJailTurn(0);
            userIO.showMessage("Du slap ud af fængsel");
        } else if (position == 30){
            userIO.moveCar(model);
            userIO.showMessage("You have been put in jail :(");
            currentPlayer.setPosition(10);
            currentPlayer.setInJail(true);
            currentPlayer.setInJailTurn(0);
            userIO.moveCar(model);
        }
    }

    public void taxFields(Model model) {
        Player currentPlayer = model.getPlayerCurrentTurn();
        userIO.moveCar(model);
        if (currentPlayer.getPosition() == 4) {
            String[] options = {"10%", "4000$"};
            String option = userIO.getUserButtonPressed("Income tax: Pay 10% of your total assets or 4000$", "10%", "4000$");
            if (option.equals(options[0])){
                int tempBalance = (int) Math.round(currentPlayer.getValueOfAllAssets() * 0.9);
                tempBalance = tempBalance - (tempBalance % 100);
                currentPlayer.addPlayerBalance(tempBalance - currentPlayer.getValueOfAllAssets());
            }
            else {
                userIO.showMessage("You have paid 4000$");
                currentPlayer.addPlayerBalance(-4000);
            }
        }else { userIO.showMessage("Special-tax, press OK to pay 2000$");
        }
    }


    private void chanceCardField(Model model){

        Player currentplayer = model.getPlayerCurrentTurn();
        userIO.moveCar(model);
        userIO.showChanceCard(model.getDeck().drawCard().toString());
        userIO.showMessage("You have drawn a chance card!");
        System.out.println(model.getDeck().getLastCard().getIndex() + " is index");

        switch(model.getDeck().getLastCard().getIndex() + 1){
            case 1:
                return;
            case 2:
                return;
            case 3:
            case 8:
            case 10:
                currentplayer.addPlayerBalance(-1000);
                return;
            case 4:
                currentplayer.addPlayerBalance(-300);
                return;
            case 5:
            case 9:
            case 11:
                currentplayer.addPlayerBalance(-200);
                return;
            case 6:
            case 7:
                currentplayer.addPlayerBalance(-3000);
                return;
            case 12:
                currentplayer.addPlayerBalance(-2000);
                return;
            case 13:
            case 14:
                currentplayer.addPlayerBalance(500);
                return;
            case 15:
            case 16:
            case 17:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                currentplayer.addPlayerBalance(1000);
                return;
            case 18:
                currentplayer.addPlayerBalance(3000);
                return;
            case 24:
                currentplayer.addPlayerBalance(200);
                return;
            case 25:
                if(currentplayer.getValueOfAllAssets() > 15000){
                    return;
                } else {
                    currentplayer.addPlayerBalance(40000);
                }
            case 26:
                return;
            case 29:
            case 30:
                currentplayer.setPosition(0);
                currentplayer.addPlayerBalance(4000);
                userIO.moveCar(model);
                return;
            case 31:
                currentplayer.addPosition(3);
                userIO.moveCar(model);
                return;
            case 32:
            case 33:
                currentplayer.addPosition(-3);
                userIO.moveCar(model);
                return;
            case 34:
            case 35:
                if(currentplayer.getPosition() > 11){
                    currentplayer.addPlayerBalance(4000);
                }
                currentplayer.setPosition(11);
                userIO.moveCar(model);
                return;
            case 36:
                return;
            case 37:
                if(currentplayer.getPosition() > 15){
                    currentplayer.addPlayerBalance(4000);
                }
                currentplayer.setPosition(15);
                userIO.moveCar(model);
                return;
            case 38:
                if(currentplayer.getPosition() > 24){
                    currentplayer.addPlayerBalance(4000);
                }
                currentplayer.setPosition(24);
                userIO.moveCar(model);
                return;
            case 39:
                if(currentplayer.getPosition() > 32){
                    currentplayer.addPlayerBalance(4000);
                }
                currentplayer.setPosition(32);
                userIO.moveCar(model);
                return;
            case 40:
                if(currentplayer.getPosition() > 35){
                    currentplayer.addPlayerBalance(4000);
                    currentplayer.setPosition(5);
                } else if (currentplayer.getPosition() > 5) {
                    currentplayer.setPosition(15);
                } else if (currentplayer.getPosition() > 15) {
                    currentplayer.setPosition(25);
                }else{
                    currentplayer.setPosition(35);
                }
                userIO.moveCar(model);
                return;
            case 41:
                if(currentplayer.getPosition() > 19){
                    currentplayer.addPlayerBalance(4000);
                }
                currentplayer.setPosition(19);
                userIO.moveCar(model);
                return;
            case 42:
                currentplayer.setPosition(39);
                userIO.moveCar(model);
                return;
            case 43:
                currentplayer.setHasJailCard(true);
                return;
            case 44:
            case 45:
                currentplayer.setInJail(true);
                currentplayer.setPosition(10);
                currentplayer.setInJailTurn(0);
                userIO.moveCar(model);




        }


    }


}
