package model;

public class Account {
    private int Balance = 40000;
    private int AssetsValue = 40000;

    public int getBalance(){
        return this.Balance;
    }

    // only for test/game start
    public void setBalance(int newPlayerBallance){
        this.Balance = newPlayerBallance;
        this.AssetsValue = newPlayerBallance;
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
