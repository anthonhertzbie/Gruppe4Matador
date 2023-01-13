package model;

import java.util.Arrays;

public class Gameboard {
    private Helper helper = new Helper();
    private Field[] fields = new Field[40];
    private int[][] prices = new int[40][8];
    private boolean[][] ownerTable = new boolean[40][6];
    private String[] fieldType = new String[40];
    private int[] ownerOfFieldgroups = new int[8];
    private int[] ownerOfField = new int[40];

    public Gameboard(){
        Arrays.fill(ownerOfFieldgroups, -1);
        Arrays.fill(ownerOfField, -1);
        copyFieldInformation();
        initFieldPrice();
        initHousePrice();
        initRent();
        initOwner();
    }
    public Field getField(int index){
        return fields[index];
    }

    /**
     * Sells all property
     */
    private void initOwner(){
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 6; j++) {
                ownerTable[i][j] = false;
            }
            System.out.println(ownerOfField[i]);
        }
    }


    public boolean ownerOfAll(int playerIndex, int field){
        boolean ownerOfAll = false;
        switch (field) {
            case 1, 3 -> {
                ownerOfAll = ownerTable[1][playerIndex] && ownerTable[3][playerIndex];
                return ownerOfAll;
            }
            case 6, 8, 9 -> {
                ownerOfAll = ownerTable[6][playerIndex] && ownerTable[8][playerIndex] && ownerTable[9][playerIndex];
                return ownerOfAll;
            }
            case 11, 13, 14 -> {
                ownerOfAll = ownerTable[11][playerIndex] && ownerTable[13][playerIndex] && ownerTable[14][playerIndex];
                return ownerOfAll;
            }

            case 16, 18, 19 -> {
                ownerOfAll = ownerTable[16][playerIndex] && ownerTable[18][playerIndex] && ownerTable[19][playerIndex];
                return ownerOfAll;
            }
            case 21, 23, 24 -> {
                ownerOfAll = ownerTable[21][playerIndex] && ownerTable[23][playerIndex] && ownerTable[24][playerIndex];
                return ownerOfAll;
            }
            case 26, 27, 29 -> {
                ownerOfAll = ownerTable[26][playerIndex] && ownerTable[27][playerIndex] && ownerTable[29][playerIndex];
                return ownerOfAll;
            }
            case 31, 32, 34 -> {
                ownerOfAll = ownerTable[31][playerIndex] && ownerTable[32][playerIndex] && ownerTable[34][playerIndex];
                return ownerOfAll;
            }
            case 37, 39 -> {
                ownerOfAll = ownerTable[37][playerIndex] && ownerTable[39][playerIndex];
                return ownerOfAll;
            }
        }
        return ownerOfAll;
    }
    /*
    blue = 1,3
    yellow = 6, 8, 9
    other yellow = 11, 13, 14
    gray = 16, 18, 19
    red = 21, 23, 24
    light gray = 26, 27, 29
    yellow = 31, 32, 34
    purple = 37, 39
     */


    public boolean isOwned(int fieldIndex) {
        boolean isOwned = false;
        for (int i = 0; i < 6; i++) {
            if (ownerTable[fieldIndex][i]) {
                isOwned = ownerTable[fieldIndex][i];
                break;
            }
        }
        return isOwned;
    }
    public int whoOwnsThis(int fieldIndex){
        int playerIndex = 7;
        for (int i = 0; i < 6; i++) {
            if(ownerTable[fieldIndex][i]){
                playerIndex = i;
            }
        }
        return playerIndex;
    }
    public void buyField(int fieldIndex, int playerIndex){
        ownerTable[fieldIndex][playerIndex] = true;
    }
    public void sellField(int fieldIndex, int playerIndex){
        ownerTable[fieldIndex][playerIndex] = false;
    }


    public void rentIncrease(int fieldIndex){
            for (int i = 2; i < 7; i++) {
                if (fields[fieldIndex].getCurrentRent() == prices[fieldIndex][i]) {
                    fields[fieldIndex].setCurrentRent(prices[fieldIndex][i + 1]);
                    return;
                }
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
    public String getFieldName(int fieldIndex){
        return fields[fieldIndex].getTitleOf();
    }
    public int getFieldCurrentRent(int fieldIndex){
        return fields[fieldIndex].getCurrentRent();
    }
    public int getSpecificPrice(int fieldIndex, int priceNo){
        return prices[fieldIndex][priceNo];
    }
    private void copyFieldInformation(){
        copyTitels();
        copyNumbers();
        copyFieldType();
    }
    private void copyTitels(){
        for (int i = 0; i < 40; i++) {
            fields[i] = new Field(helper.getFieldData(i + 1, 0));
        }
    }
    private void copyFieldType(){
        for (int i = 0; i < 40; i++) {
            fieldType[i] = helper.getFieldData(i + 1, 2);
            System.out.println(fieldType[i]);
        }
    }

    private void copyNumbers(){
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 8; j++) {
                prices[i][j] = Integer.parseInt(helper.getFieldData(i + 1, j + 3));
            }
        }
    }

    public String getFieldType(int index){
        return fieldType[index].strip();
    }


}
