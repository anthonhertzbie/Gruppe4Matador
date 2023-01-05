package org.example;

import java.util.Arrays;

public class Player {
    private String name;
    private int position;
    private boolean[] ownerOf = new boolean[40];

    private int valueOfAllAssets;
    private boolean inJail;
    private boolean hasJailCard;

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
        if(position + addPosition > 39){
            position -= 39;
            position += addPosition;
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

}
