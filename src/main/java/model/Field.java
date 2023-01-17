package model;

public class Field {
    private String titleOf;
    private int currentRent;
    private int propertyValue;

    private int housePrice;
    private int numOfHouses;
    public Field(String titleOf){
        this.titleOf = titleOf;
    }
    public int getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(int proppertyValue) {
        this.propertyValue = proppertyValue;
    }
    public String getTitleOf() {
        return titleOf;
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
