package model;

public class Model {
    private int currentTurn;
    private int previousTurn;
    private int totalPlayers;
    private Player[] players = new Player[totalPlayers];
    private Deck deck = new Deck();


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

    public void setAccountBalance(int in){
        getPlayer().setPlayerBalance(in);
    }


}
