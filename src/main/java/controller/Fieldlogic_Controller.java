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

    /**
     * Decides which method to use depending on the information stored in the model
     */
    public void specialField(){
        if(model.isChanceCard()){
            chanceCardField(model);
        }else if (model.isPrison()){
            jailField(model);
        }else if(model.isTax()){
            taxFields(model);
        }
    }

    /*
    Decides what to do if a plyer lands on a jail field
     */
    public void jailField(Model model){
        Player currentPlayer = model.getPlayerCurrentTurn();
        int position = currentPlayer.getPosition();
        int jailTurn = currentPlayer.getInJailTurn();
        String output;
        // the case when you've sat in prison for 2 rounds
        if (position == 10 && currentPlayer.isInJail() && jailTurn < 2) {
            if (currentPlayer.getHasJailCard()) {
                output = userIO.getUserButtonPressed(currentPlayer.getName() + " du er stadig i fængsel.", "Betal 1000$ for at komme ud", "Rull med terningerne", "Brug fængselskortet");
            } else {
                output = userIO.getUserButtonPressed(currentPlayer.getName() + " du er stadig i fængsel.", "Betal 1000$ for at komme ud", "Rull med terningerne");
            }

            switch (output) {
                case "Betal 1000$ for at komme ud":
                    currentPlayer.setInJail(false);
                    currentPlayer.addPlayerBalance(-1000);
                    model.addCurrentTurn(-1);
                    break;
                case "Rull med terningerne":
                    model.getCup().rollDices();
                    if(model.getCup().getDice1() == model.getCup().getDice2()) {
                        userIO.showMessage("Tillykke du er fri");
                        currentPlayer.setInJail(false);
                        currentPlayer.setInJailTurn(0);
                    } else{
                        userIO.showMessage("too bad... du slog ikke to ens ");
                    }
                    break;
                case "Brug fængselskortet":
                    currentPlayer.setInJail(false);
                    userIO.removeJailcard(model);
                    model.addCurrentTurn(-1);
                    break;
            }
            currentPlayer.setInJailTurn(currentPlayer.getInJailTurn() + 1);
        } else if (position == 10 && jailTurn == 2){
            userIO.showMessage("Du slap ud af fængsel");
            currentPlayer.setInJail(false);
            currentPlayer.setInJailTurn(0);
            currentPlayer.getAccount().addBalance(-1000);
            model.addCurrentTurn(-1);
        } else if (position == 30){
            userIO.showMessage("Du er røget i fængsel.");
            currentPlayer.setPosition(10);
            currentPlayer.setInJail(true);
            currentPlayer.setInJailTurn(0);
        }
    }

    /**
     *  Decides what to do if player lands on a tax field
     * @param model
     */
    public void taxFields(Model model) {
        Player currentPlayer = model.getPlayerCurrentTurn();
        if (currentPlayer.getPosition() == 4) {
            String[] options = {"10%", "4000$"};
            String option = userIO.getUserButtonPressed("Indkomstskat: betal 10% af din samlede pengebeholdning eller 4000$", "10%", "4000$");
            if (option.equals(options[0])){
                int tempBalance = (int) Math.round(currentPlayer.getPlayerBalance() * 0.9);
                tempBalance = tempBalance - (tempBalance % 100);
                currentPlayer.addPlayerBalance(tempBalance - currentPlayer.getPlayerBalance());
            }
            else {
                userIO.showMessage("Du har betalt 4000$");
                currentPlayer.addPlayerBalance(-4000);
            }
        }else { userIO.showMessage("Ekstraordinær statsskat, tryk OK for at betale 2000$");
        }
    }

    /**
     * Chance card effects
     * @param model
     */

    public void chanceCardField(Model model){

        Player currentplayer = model.getPlayerCurrentTurn();
        String message = model.getDeck().drawCard().toString();
        int cardNo = model.getDeck().getLastCard().getCardNumber();
        if(cardNo == 1 || cardNo == 2 || cardNo == 26 || cardNo == 27 || cardNo == 36){
            chanceCardField(model);
        }
        userIO.showChanceCard(message);
        userIO.showMessage("Du har trukket et chancekort!");
        System.out.println(model.getDeck().getLastCard().getCardNumber() + " is index");
        switch(model.getDeck().getLastCard().getCardNumber()){
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
                if(currentplayer.getPlayerBalance() > 15000){
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
                return;
            case 32:
            case 33:
                currentplayer.addPosition(-3);
                return;
            case 34:
            case 35:
                if(currentplayer.getPosition() > 11){
                    currentplayer.addPlayerBalance(4000);
                }
                currentplayer.setPosition(11);
                return;
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
                userIO.addJailCard(model);
                return;
            case 44:
            case 45:
                currentplayer.setInJail(true);
                currentplayer.setPosition(10);
                currentplayer.setInJailTurn(0);




        }


    }


}
