package model;

public class Model {
    private int currentTurn;
    private int totalPlayerCount = 1;
    private Player[] players;
    private Deck deck = new Deck();
    private Cup cup = new Cup();
    private boolean startGUI, normalTurn;

    public void setStartGUI(boolean startGUI){
        this.startGUI = startGUI;
    }


    public void setPlayerPosition(int diceThrow){
        players[currentTurn].addPosition(diceThrow);
    }


    public boolean getStartGUI(){
        return startGUI;
    }

    public int getCurrentTurn(){
        return this.currentTurn;
    }

    public Player getPlayerCurrentTurn(){
        return players[currentTurn];
    }

    public Player getPlayerByIndex(int index){
        return players[index];
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

    public void setPlayerBalance(int index, int playerBalance){
        players[index].setPlayerBalance(playerBalance);
    }

    public void setTotalPlayerCount(int playerCount){
        totalPlayerCount = playerCount;
        players = new Player[totalPlayerCount];
    }
    public int getTotalPlayerCount(){
        return totalPlayerCount;
    }


    public void setPlayerName(int index, String names){
        players[index] = new Player();
        players[index].setName(names);
    }


    public boolean getNormalTurn() {
        return normalTurn;
    }

    public String getPlayerName(int index){
        return players[index].getName();
    }

    public void setNormalTurn(boolean normalTurn) {
        this.normalTurn = normalTurn;
    }

    public Cup getCup() {
        return cup;
    }

}
