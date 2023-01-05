package model;

public class Account {
    private int Balance = 0;
    private int AssetsValue = 0;

    public int getBalance(){
        return this.Balance;
    }
    public void setBalance(int newPlayerBallance){
        this.Balance = newPlayerBallance;
    }
    public void addBalance(int amount){
        this.Balance += amount;
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
