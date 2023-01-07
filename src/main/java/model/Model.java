package model;

import controller.Notifier;

public class Model {
    private int currentTurn;
    private int previousTurn;
    private int totalPlayers = 1;
    private Player[] players = new Player[totalPlayers];
    private Deck deck = new Deck();
    private Cup cup = new Cup();
    private String textInput;

    private Notifier notifier;



    public void turn(){
        currentTurn += 1;
    }
    public int getCurrentTurn(){
        return currentTurn;
    }
    public int getPreviousTurn(){
        return previousTurn;
    }
    public int getTotalPlayers(){
        return totalPlayers;
    }
    public Player getPlayer(){
        return players[currentTurn];
    }
    public Deck getDeck(){
        return deck;
    }
    public Cup getCup(){return cup;}

    public void setTextInput(String textInput){
        this.textInput = textInput;
    }

    public String getShowMessage(){

        return textInput;
    }
    public void setShowMessages(String text){
        this.textInput = text;
    }

    public void addPlayer(int i, String name){
        players[i].setName(name);
    }

    public void setAccountBalance(int in){
        getPlayer().setPlayerBalance(in);
    }



}
