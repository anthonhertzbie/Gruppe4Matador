package MockUp;


import controller.Notifier;

public class Model {
    private int currentTurn = 0;
    private int previousTurn = 0;
    private int totalPlayers = 1;
    private player[] players = new player[4];
    private int playerTurnTest = 0;
    private String[] userButton;
    Notifier notifier;


    public void turn() {
        currentTurn += 1;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public int getPreviousTurn() {
        return previousTurn;
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public player getPlayer() {
        return players[this.playerTurnTest];
    }

    public void startGame(){

    }

    public void setShowMessage(String text){
        this.showMessage = text;
    }

    public String getShowMessage(){
        return this.showMessage;
    }
    public void setUserButton(String text, String ... options){
        this.userButton[0] = text;
    }

}

