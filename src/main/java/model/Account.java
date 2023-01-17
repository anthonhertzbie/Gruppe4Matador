package model;

public class Account {
    private int Balance = 4000;
    public int getBalance(){
        return this.Balance;
    }

    /**
     * Disregards the previous balance and sets the new.
     * @param newPlayerBalance the new balance
     */
    public void setBalance(int newPlayerBalance){
        if (newPlayerBalance < 0) {
            throw new UnsupportedOperationException("Expected the new balance to be positive");
        }
        this.Balance = newPlayerBalance;

    }

    /**
     * works when you buy a field or pays rent
     * @param amount the amount to be added to the account.
     */
    public void addBalance(int amount){
        this.Balance += amount;
    }

    /**
     * Pays for the house
     * @param houseCost the house price
     */
    public void payForHouse(int houseCost){
        this.Balance -= houseCost;
    }

    /**
     * Method for when a player looses their house to the bank
     * @param houseCost the house price
     */
    public void sellHouse(int houseCost){
        this.Balance += 0.5*houseCost;
    }

}
