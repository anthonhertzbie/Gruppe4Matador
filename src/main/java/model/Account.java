package model;

public class Account {
    private int Balance = 40000;
    private int AssetsValue = 40000;

    public int getBalance(){
        return this.Balance;
    }
    public void setBalance(int newPlayerBalance){
        if (newPlayerBalance < 0) {
            throw new UnsupportedOperationException("Expected the new balance to be positive");
        }
        this.Balance = newPlayerBalance;
        this.AssetsValue = newPlayerBalance;
    }
    // works when you buy a field or pays rent
    public void addBalance(int amount){
        this.Balance += amount;
        this.AssetsValue += amount;
    }

    public void payForHouse(int houseCost){
        this.Balance -= houseCost;
        this.AssetsValue += houseCost;
    }





    public int getAssetsValue(){
        return this.AssetsValue;
    }
    public void setAssetsValue(int newTotalAssets){
        this.AssetsValue = newTotalAssets;
    }
    public void addAssetsValue(int amount){
        this.AssetsValue += amount;
    }
}
