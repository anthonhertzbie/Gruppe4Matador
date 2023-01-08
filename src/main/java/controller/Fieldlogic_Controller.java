package controller;

import model.Model;
import model.Player;

public class Fieldlogic_Controller {
    private Model model;

    public Fieldlogic_Controller(Model model){
        this.model = model;
    }

    public void specialField(){
        if(model.isChanceCard()){
            chanceCardField(model);
        }
    }
    private void chanceCardField(Model model){
        Player currentplayer = model.getPlayerCurrentTurn();
        switch(model.getDeck().getLastCard().getIndex()){
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
                return;
            case 31:
                currentplayer.addPosition(3);
            case 32:
            case 33:
                currentplayer.addPosition(-3);
            case 34:
                if(currentplayer.getPosition() > 11){
                    currentplayer.addPlayerBalance(4000);
                }
                currentplayer.setPosition(11);
            case 35:
            case 36:
                return;
            case 37:
                if(currentplayer.getPosition() > 15){
                    currentplayer.addPlayerBalance(4000);
                }
                currentplayer.setPosition(15);
                return;
            case 38:
                if(currentplayer.getPosition() > 24){
                    currentplayer.addPlayerBalance(4000);
                }
                currentplayer.setPosition(24);
                return;
            case 39:
                if(currentplayer.getPosition() > 32){
                    currentplayer.addPlayerBalance(4000);
                }
                currentplayer.setPosition(32);
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
                return;
            case 41:
                if(currentplayer.getPosition() > 19){
                    currentplayer.addPlayerBalance(4000);
                }
                currentplayer.setPosition(19);
                return;
            case 42:
                currentplayer.setPosition(39);
                return;
            case 43:
                currentplayer.setHasJailCard(true);
                return;
            case 44:
            case 45:
                currentplayer.setInJail(true);
        }
    }
    private void taxField(){

    }
}
