package model;

import controller.Notifier;

public class Model {
    private int currentTurn;
    private int totalPlayerCount = 1;
    private Player[] players;
    private Deck deck = new Deck();
    private Cup cup = new Cup();
    private String textInput;
    private Notifier notifier;
    private boolean startGUI, normalTurn;

    public void setStartGUI(boolean startGUI){
        this.startGUI = startGUI;
    }


    public void setPlayerPosition(int diceThrow){
        players[currentTurn].addPosition(diceThrow);
    }

    public int getCurrentPosition(){
        return players[currentTurn].getPosition();
    }

    public int getPreviousPosition(){
        return  players[currentTurn].getPreviousPosition();
    }

    public boolean getStartGUI(){
        return startGUI;
    }

    public int getCurrentTurn(){
        return this.currentTurn;
    }

    public void changeTurn(){
        System.out.println(currentTurn);
        if (currentTurn < totalPlayerCount - 1) {
            currentTurn += 1;
        } else{
            currentTurn = totalPlayerCount - currentTurn - 1;
        }
    }

    public int getPlayerBalance(int i){
        return players[i].getPlayerBalance();
    }

    public void setPlayerBalance(int i, int playerBalance){
        players[i].setPlayerBalance(playerBalance);
    }

    public void setTotalPlayerCount(int playerCount){
        totalPlayerCount = playerCount;
        players = new Player[totalPlayerCount];
    }
    public int getTotalPlayerCount(){
        return totalPlayerCount;
    }
    public Player getPlayer(){
        return players[currentTurn];
    }

    public String getShowMessage(){
        return textInput;
    }

    public void setPlayerName(int index, String names){
        players[index] = new Player();
        players[index].setName(names);
    }


    public boolean getNormalTurn() {
        return normalTurn;
    }

    public String getPlayerNames(int index){
        return players[index].getName();
    }

    public void setNormalTurn(boolean normalTurn) {
        this.normalTurn = normalTurn;
    }

    public Cup getCup() {
        return cup;
    }

    public void setCup(Cup cup) {
        this.cup = cup;
    }
}
