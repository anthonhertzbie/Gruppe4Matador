package org.example;

public class Account {
    private int playerBallance = 0;
    private int playerTotalAssets = 0;

    public int getPlayerBallance(){
        return this.playerBallance;
    }
    public void setPlayerBallance(int newPlayerBallance){
        this.playerBallance = newPlayerBallance;
    }
    public void addPlayerbalance(int ammount){
        this.playerBallance += ammount;
    }
    public int getPlayerTotalAssets(){
        return this.playerTotalAssets;
    }
    public void setPlayerTotalAssets(int newTotalAssets){
        this.playerTotalAssets = newTotalAssets;
    }
    public void addPlayerTotalAssets(int ammount){
        this.playerTotalAssets += ammount;
    }
}
