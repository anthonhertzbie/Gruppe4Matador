package model;

public class Model {
    private int currentTurn;
    private int previousTurn;
    private int totalPlayers;
    private Player[] players = new Player[totalPlayers];
    private Deck deck = new Deck();
    private Cup cup = new Cup();
    private String textInput;


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

    public String showMessage(){
        return textInput;
    }


    public void setAccountBalance(int in){
        getPlayer().setPlayerBalance(in);
    }


}
