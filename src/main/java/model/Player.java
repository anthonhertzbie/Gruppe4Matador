package model;

import java.util.Arrays;

public class Player {
    private String name;
    private int position = 0;
    private int previousPosition = 0;
    private int previousPositionChanceCard;
    private boolean[] ownerOf = new boolean[40];
    private boolean inJail = false;
    private boolean hasJailCard = false;
    private int inJailTurn;

    private final Account account;


    public Player(){
        this(new Account());

    }

    //Makes sure that player takes an account as an input//
    public Player(Account account){
        this.account = account;
        Arrays.fill(ownerOf, false);
    }

    public void setName(String playerName){
        this.name = playerName;
    }
    public String getName(){
        return this.name;
    }
    public void setPosition(int position){
        this.previousPositionChanceCard = this.position;
        System.out.println(previousPosition + "This is the previous position after setPosition");
        this.position = position;
    }
    public int getPreviousPositionChanceCard(){
        return previousPositionChanceCard;
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
    public int getValueOfAllAssets(){
        return account.getAssetsValue();
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
        System.out.println("injailTurn is : " + this.inJailTurn + " inJailTurn sat to : " + inJailTurn);
        this.inJailTurn = inJailTurn;
    }
}
