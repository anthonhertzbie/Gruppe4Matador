package model;

public class Field {
    public String getTitleOf() {
        return titleOf;
    }

    public void setTitleOf(String titleOf) {
        this.titleOf = titleOf;
    }

    private String titleOf;
    private int currentRent;
    private int numOfHouses;
    public Field(String titleOf){
        this.titleOf = titleOf;
    }

    public int getCurrentRent() {
        return currentRent;
    }

    public void setCurrentRent(int currentRent) {
        this.currentRent = currentRent;
    }

    public int getNumOfHouses() {
        return numOfHouses;
    }

    public void setNumOfHouses(int numOfHouses) {
        this.numOfHouses = numOfHouses;
    }


}
