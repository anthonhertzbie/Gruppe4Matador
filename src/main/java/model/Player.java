package model;

public class Player {
    private String name;
    private int position = 0;
    private int previousPosition = 0;
    private int previousPositionChanceCard;
    private int doubleTurn = 0;
    private boolean inJail = false;
    private boolean hasJailCard = false;
    private int inJailTurn;
    private boolean hasLost = false;
    private int playerID;
    private int totalHouses = 0;

    private Account account = new Account();

    public void setName(String playerName){
        this.name = playerName;
    }
    public String getName(){
        return this.name;
    }
    public void setPosition(int position){
        this.previousPositionChanceCard = this.position;
        this.position = position;
    }


    public Account getAccount() {
        return account;
    }

    public void addPosition(int addPosition){
        previousPosition = position;
        if(position + addPosition > 39){
            position -= 39;
            position += addPosition;
            addPlayerBalance(4000);
        } else if (position + addPosition < 0) {
            position += addPosition + 40;
        } else {
            position += addPosition;
        }

    }
    public int getPosition(){
        return position;
    }
    public void setInJail(boolean isInJail){
        this.inJail = isInJail;
    }
    public boolean isInJail(){
        return this.inJail;
    }
    public void setHasJailCard(boolean hasJailCard){
        this.hasJailCard = hasJailCard;
    }
    public boolean getHasJailCard(){
        return this.hasJailCard;
    }
    public int getPlayerBalance(){
        return account.getBalance();
    }
    public void addPlayerBalance(int amount){
        account.addBalance(amount);
    }

    public int getInJailTurn() {
        return inJailTurn;
    }

    public void setInJailTurn(int inJailTurn) {
        this.inJailTurn = inJailTurn;
    }

    public boolean getHasLost() {
        return hasLost;
    }

    public void setHasLost(boolean hasLost) {
        this.hasLost = hasLost;
    }

    public int getDoubleTurn() {
        return doubleTurn;
    }

    public void addDoubleTurn(int doubleTurn) {
        this.doubleTurn += doubleTurn;
    }

    public void setDoubleTurn(int doubleTurn){
        this.doubleTurn = doubleTurn;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }


    public int getTotalHouses() {
        return totalHouses;
    }

    public void setTotalHouses(int totalHouses) {
        this.totalHouses = totalHouses;
    }
}
