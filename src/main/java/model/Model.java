package model;

public class Model {
    private int currentTurn;
    private int totalPlayerCount = 1;
    private Player[] players;
    private Deck deck = new Deck();
    private Cup cup = new Cup();
    private boolean startGUI, normalTurn, isChanceCard, isPrison, isShipping, isBrewery, isParking, isFerry, isTax;

    public void setStartGUI(boolean startGUI){
        this.startGUI = startGUI;
    }


    public void setPlayerPosition(int diceThrow) {
        players[currentTurn].addPosition(diceThrow);
    }
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
    public void resetBooleans(){
        startGUI = false;
        normalTurn = false;
        isChanceCard = false;
        isPrison = false;
        isShipping = false;
        isBrewery = false;
        isParking = false;
        isFerry = false;
        isTax = false;
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
    public boolean isStartGUI() {
        return startGUI;
    }

    public boolean isNormalTurn() {
        return normalTurn;
    }

    public boolean isChanceCard() {
        return isChanceCard;
    }

    public void setChanceCard(boolean chanceCard) {
        isChanceCard = chanceCard;
    }

    public boolean isPrison() {
        return isPrison;
    }

    public void setPrison(boolean prison) {
        isPrison = prison;
    }

    public boolean isShipping() {
        return isShipping;
    }

    public void setShipping(boolean shipping) {
        isShipping = shipping;
    }

    public boolean isBrewery() {
        return isBrewery;
    }

    public void setBrewery(boolean brewery) {
        isBrewery = brewery;
    }

    public boolean isParking() {
        return isParking;
    }

    public void setParking(boolean parking) {
        isParking = parking;
    }

    public boolean isFerry() {
        return isFerry;
    }

    public void setFerry(boolean ferry) {
        isFerry = ferry;
    }

    public boolean isTax() {
        return isTax;
    }

    public void setTax(boolean tax) {
        isTax = tax;
    }

}
