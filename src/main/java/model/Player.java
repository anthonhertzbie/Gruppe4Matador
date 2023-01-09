package model;

import java.util.Arrays;

public class Player {
    private String name;
    private int position = 0;
    private int previousPosition = 0;
    private boolean[] ownerOf = new boolean[40];

    private int valueOfAllAssets;
    private boolean inJail = false;
    private boolean hasJailCard = false;
    private int inJailTurn;

    private Account account = new Account();


    public Player(){
        this.account.setBalance(0);
        Arrays.fill(ownerOf, false);

    }

    public void setName(String playerName){
        this.name = playerName;
    }
    public String getName(){
        return this.name;
    }
    public void setPosition(int position){
        this.position = position;
    }
    public void addPosition(int addPosition){
        previousPosition = position;
        if(position + addPosition > 39){
            position -= 39;
            position += addPosition;
            addPlayerBalance(4000);
        } else if (position + addPosition < 0) {
            position = addPosition + 40;
        } else {
            position += addPosition;
        }

    }
    public int getPreviousPosition(){
        return previousPosition;
    }
    public int getPosition(){
        return position;
    }
    public void setInJail(boolean isInJail){
        this.inJail = isInJail;
    }
    public boolean getInJail(){
        return this.inJail;
    }
    public void setHasJailCard(boolean hasJailCard){
        this.hasJailCard = hasJailCard;
    }
    public boolean getHasJailCard(){
        return this.hasJailCard;
    }
    //Account getters and setters
    private void updateTotalAssets(){
        valueOfAllAssets = account.getAssetsValue() + account.getBalance();
    }
    public int getValueOfAllAssets(){
        return valueOfAllAssets;
    }
    public void setPlayerBalance(int newPlayerBalance){
        account.setBalance(newPlayerBalance);
    }
    public int getPlayerBalance(){
        return account.getBalance();
    }
    public void addPlayerBalance(int amount){
        account.addBalance(amount);
    }
    public void set(){

    }

    public int getInJailTurn() {
        return inJailTurn;
    }

    public void setInJailTurn(int inJailTurn) {
        this.inJailTurn = inJailTurn;
    }
}
