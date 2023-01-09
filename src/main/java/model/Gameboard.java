package model;

public class Gameboard {
    private Helper helper = new Helper();
    private Field[] fields = new Field[41];
    private int[][] prices = new int[41][8];

    public Gameboard(){
        copyFieldInformation();
        initFieldPrice();
        initHousePrice();

    }


    public void rentIncrease(int fieldIndex){
        if(fields[fieldIndex].getPropertyValue() == 0){
            return;
        } else{

        }
    }
    private void initHousePrice(){
        for (int i = 0; i < fields.length - 1; i++) {
            fields[i].setPropertyValue(prices[i][1]);
        }
    }
    private void initRent(){
        for (int i = 0; i < fields.length - 1; i++) {
            if (fields[i].getPropertyValue() != 0) {
                fields[i].setCurrentRent(prices[i][2]);
            }
        }
    }
    private void initFieldPrice(){
        for (int i = 0; i < fields.length - 1; i++) {
            fields[i].setPropertyValue(prices[i][0]);
        }
    }
    private void copyFieldInformation(){
        copyTitels();
        copyNumbers();
    }
    private void copyTitels(){
        for (int i = 0; i < 40; i++) {
            fields[i] = new Field(helper.getFieldData(i + 1, 0));
        }
    }
    private void copyNumbers(){
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 8; j++) {
                prices[i][j] = Integer.parseInt(helper.getFieldData(i + 1, j + 3));
            }
        }
    }
}
