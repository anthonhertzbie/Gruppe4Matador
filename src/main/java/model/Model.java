package model;

public class Model {
    private int currentTurn;
    private int totalPlayerCount = 1;
    private Player[] players;
    private Deck deck = new Deck();
    private Cup cup = new Cup();
    private Gameboard gameBoard = new Gameboard();
    private boolean startGUI, normalTurn = true, isChanceCard, isPrison, isShipping, isBrewery, isParking, isTax, gameIsOver;



    public Gameboard gameBoard(){
        return this.gameBoard;
    }


    public void setPlayerPosition(int diceThrow) {
        players[currentTurn].addPosition(diceThrow);
    }

    /**
     * Method for keeping track of the current turn.
     * @param plusminus the number of turns that you want to add.
     */
    public void addCurrentTurn(int plusminus){
        if (this.currentTurn + plusminus < 0){
            this.currentTurn += plusminus + totalPlayerCount;
        }else {
        this.currentTurn += plusminus;
        }
    }

    /**
     * depending on where your position (as a player) is on the board then the state gets updated appropriately
     */
    public void setBooleans(){
        int position = players[currentTurn].getPosition();
        if (position == 2 || position == 7 || position == 17 || position == 22 || position == 33 || position == 36) {
            isChanceCard = true;
            return;
        }
        if (position == 4 || position == 38) {
            isTax = true;
            return;
        }
        if (position == 5 || position == 15 || position == 25 || position == 35) {
            isShipping = true;
            return;
        }
        if (position == 10 || position == 30) {
            isPrison = true;
            return;
        }
        if (position == 20) {
            isParking = true;
            return;
        }
        if (position == 12 || position == 28) {
            isBrewery = true;
            return;
        }
        normalTurn = true;
    }

    /**
     * Resets all booleans and helps keep track of the game state, so that we don't have multiple states at once
     */
    public void resetBooleans(){
        startGUI = false;
        normalTurn = false;
        isChanceCard = false;
        isPrison = false;
        isShipping = false;
        isBrewery = false;
        isParking = false;
        isTax = false;
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
        if (currentTurn < totalPlayerCount - 1) {
            currentTurn += 1;
        } else{
            currentTurn = totalPlayerCount - currentTurn - 1;
        }
    }

    public int getPlayerBalance(int i){
        return players[i].getPlayerBalance();
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


    public String getPlayerName(int index){
        return players[index].getName();
    }

    public void setNormalTurn(boolean normalTurn) {
        this.normalTurn = normalTurn;
    }

    public Cup getCup() {
        return cup;
    }
    public Deck getDeck(){
        return deck;
    }

    public boolean isChanceCard() {
        return isChanceCard;
    }

    public boolean isPrison() {
        return isPrison;
    }

    public void setPrison(boolean prison) {
        isPrison = prison;
    }


    public boolean isTax() {
        return isTax;
    }

    public boolean isGameIsOver() {
        return gameIsOver;
    }

    public void setGameIsOver(boolean gameIsOver) {
        this.gameIsOver = gameIsOver;
    }

    public boolean hasPlayer(int i) {
        return players[i] != null;
    }
}
