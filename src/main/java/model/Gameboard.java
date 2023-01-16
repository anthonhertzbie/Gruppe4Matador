package model;

import java.util.Arrays;

public class Gameboard {
    private Helper helper = new Helper();
    private Field[] fields = new Field[40];
    private int[][] prices = new int[40][8];
    private int[] ownerTable = new int[40];
    private String[] fieldType = new String[40];
    private int[] ownerOfFieldgroups = new int[8];

    public Gameboard(){
        Arrays.fill(ownerOfFieldgroups, -1);
        copyFieldInformation();
        initFieldPrice();
        initHousePrice();
        initRent();
        initOwner();
    }



    public void setOwnerOfFieldgroups(int playerIndex, int group){
        this.ownerOfFieldgroups[group] = playerIndex;
    }

    public int[] getOwnerOfFieldGroups(){
        return ownerOfFieldgroups;
    }

    public int[] getFieldGroup(int fieldIndex){
        switch (fieldIndex){
            case 1, 3 -> {
                return new int[] {1,3};
            }
            case 6, 8, 9 -> {
                return new int[]{6,8,9};
            }
            case 11, 13, 14 -> {
                return new int[]{11,13,14};
            }
            case 16, 18, 19 -> {
                return new int[]{16,18,19};
            }
            case 21, 23, 24 -> {
                return new int[]{21,23,24};
            }
            case 26, 27, 29 -> {
                return new int[]{26,27,29};
            }
            case 31, 32, 34 -> {
                return new int[]{31,32,34};
            }
            case 37, 39 -> {
                return new int[]{37,39};
            }
        }
        return new int[]{};

    }

    public boolean checkIfFieldGroupOwned(int fieldIndex){
        boolean fieldGroupOwned = false;
        switch (fieldIndex){
            case 1, 3 -> {
                if (ownerOfFieldgroups[0] != -1){fieldGroupOwned = true;}
                return fieldGroupOwned;
            }
            case 6, 8, 9 -> {
                if (ownerOfFieldgroups[1] != -1){fieldGroupOwned = true;}
                return fieldGroupOwned;
            }
            case 11, 13, 14 -> {
                if (ownerOfFieldgroups[2] != -1){fieldGroupOwned = true;}
                return fieldGroupOwned;
            }
            case 16, 18, 19 -> {
                if (ownerOfFieldgroups[3] != -1){fieldGroupOwned = true;}
                return fieldGroupOwned;
            }
            case 21, 23, 24 -> {
                if (ownerOfFieldgroups[4] != -1){fieldGroupOwned = true;}
                return fieldGroupOwned;
            }
            case 26, 27, 29 -> {
                if (ownerOfFieldgroups[5] != -1){fieldGroupOwned = true;}
                return fieldGroupOwned;
            }
            case 31, 32, 34 -> {
                if (ownerOfFieldgroups[6] != -1){fieldGroupOwned = true;}
                return fieldGroupOwned;
            }
            case 37, 39 -> {
                if (ownerOfFieldgroups[7] != -1){fieldGroupOwned = true;}
                return fieldGroupOwned;
            }
        }
        return fieldGroupOwned;

    }


    public void updateFieldGroupsOwned(){
        for (int i = 0; i < getOwnerOfFieldGroups().length; i ++) {
                    if (whoOwnsThis(1) == whoOwnsThis(3) && whoOwnsThis(1) == i) {
                        setOwnerOfFieldgroups(i, 0);
                        if (whoOwnsThis(1) != whoOwnsThis(3) && whoOwnsThis(1) != i) {
                            setOwnerOfFieldgroups(-1, 0);
                        }
                    } if (whoOwnsThis(1) != whoOwnsThis(3)){
                        setOwnerOfFieldgroups(-1, 0);
                    }
                    if (whoOwnsThis(6) == whoOwnsThis(8) && whoOwnsThis(8) == whoOwnsThis(9) && whoOwnsThis(6) == i) {
                        setOwnerOfFieldgroups(i, 1);
                    }
                    if (whoOwnsThis(6) != whoOwnsThis(8) || whoOwnsThis(8) != whoOwnsThis(9)){
                        setOwnerOfFieldgroups(-1, 1);
                    }
                    if (whoOwnsThis(11) == whoOwnsThis(13) && whoOwnsThis(13) == whoOwnsThis(14) && whoOwnsThis(11) == i) {
                        setOwnerOfFieldgroups(i, 2);
                    }
                    if (whoOwnsThis(11) != whoOwnsThis(13) || whoOwnsThis(13) != whoOwnsThis(14)){
                        setOwnerOfFieldgroups(-1, 2);
                    }
                    if (whoOwnsThis(16) == whoOwnsThis(18) && whoOwnsThis(18) == whoOwnsThis(19) && whoOwnsThis(16) == i) {
                        setOwnerOfFieldgroups(i, 3);
                    } if (whoOwnsThis(16) != whoOwnsThis(18) || whoOwnsThis(18) != whoOwnsThis(19)){
                        setOwnerOfFieldgroups(-1, 3);
                    }
                    if (whoOwnsThis(21) == whoOwnsThis(23) && whoOwnsThis(23) == whoOwnsThis(24) && whoOwnsThis(21) == i) {
                        setOwnerOfFieldgroups(i, 4);
                    }  if (whoOwnsThis(21) != whoOwnsThis(23) || whoOwnsThis(23) != whoOwnsThis(24)){
                        setOwnerOfFieldgroups(-1, 4);
                    }
                    if (whoOwnsThis(26) == whoOwnsThis(27) && whoOwnsThis(27) == whoOwnsThis(29) && whoOwnsThis(26) == i) {
                        setOwnerOfFieldgroups(i, 5);
                    }  if (whoOwnsThis(26) != whoOwnsThis(27) || whoOwnsThis(27) != whoOwnsThis(29)){
                        setOwnerOfFieldgroups(-1, 5);
                    }
                    if (whoOwnsThis(31) == whoOwnsThis(32) && whoOwnsThis(32) == whoOwnsThis(34) && whoOwnsThis(31) == i) {
                        setOwnerOfFieldgroups(i, 6);
                    }  if (whoOwnsThis(31) != whoOwnsThis(32) || whoOwnsThis(32) != whoOwnsThis(34)){
                        setOwnerOfFieldgroups(-1, 6);
                    }
                    if (whoOwnsThis(37) == whoOwnsThis(39) && whoOwnsThis(37) == i) {
                        setOwnerOfFieldgroups(i, 7);
                    }  if (whoOwnsThis(37) != whoOwnsThis(38)){
                        setOwnerOfFieldgroups(-1, 7);
                    }
                }
            }




    public Field getField(int index){
        return fields[index];
    }

    /**
     * Sells all property
     */
    private void initOwner(){
        for (int i = 0; i < 40; i++) {
            ownerTable[i] = -1;
        }
    }





/*
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

 */
    /*
    blue = 1,3
    yellofw = 6, 8, 9
    other yellow = 11, 13, 14
    gray = 16, 18, 19
    red = 21, 23, 24
    light gray = 26, 27, 29
    yellow = 31, 32, 34
    purple = 37, 39
     */


    public boolean isOwned(int fieldIndex) {
        boolean isOwned = false;
        if (ownerTable[fieldIndex] != -1){
            isOwned = true;
        }
        return isOwned;
    }
    public int whoOwnsThis(int fieldIndex){
        return ownerTable[fieldIndex];
    }
    public void buyField(int fieldIndex, int playerIndex){
        ownerTable[fieldIndex] = playerIndex;
    }
    public void sellField(int fieldIndex, int playerIndex){
        ownerTable[fieldIndex] = -1;
    }

    public boolean checkForHouse(int fieldIndex){
        if (fields[fieldIndex].getNumOfHouses() != 0){
            return true;
        }
        return false;
    }

    public void rentIncrease(int fieldIndex){
        for (int i = 2; i < 7; i++) {
            if (fields[fieldIndex].getCurrentRent() == prices[fieldIndex][i]) {
                fields[fieldIndex].setCurrentRent(prices[fieldIndex][i + 1]);
                fields[fieldIndex].setNumOfHouses(fields[fieldIndex].getNumOfHouses() + 1);
                return;
            }
        }
    }

    private void rentDecrease(int fieldIndex){
        for (int i = 2; i < 7; i++) {
            if (fields[fieldIndex].getCurrentRent() == prices[fieldIndex][i]) {
                fields[fieldIndex].setCurrentRent(prices[fieldIndex][i - 1]);
                fields[fieldIndex].setNumOfHouses(fields[fieldIndex].getNumOfHouses() - 1);
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
