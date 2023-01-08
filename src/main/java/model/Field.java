package model;

import model.Helper;

public class Field {
    private String name;
    private int position;
    private String type;
    private int price;
    private int housePrice;
    private int rent0;
    private int rent1;
    private int rent2;
    private int rent3;
    private int rent4;
    private int rent5;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
    }

    public int getRent0() {
        return rent0;
    }

    public void setRent0(int rent0) {
        this.rent0 = rent0;
    }

    public int getRent1() {
        return rent1;
    }

    public void setRent1(int rent1) {
        this.rent1 = rent1;
    }

    public int getRent2() {
        return rent2;
    }

    public void setRent2(int rent2) {
        this.rent2 = rent2;
    }

    public int getRent3() {
        return rent3;
    }

    public void setRent3(int rent3) {
        this.rent3 = rent3;
    }

    public int getRent4() {
        return rent4;
    }

    public void setRent4(int rent4) {
        this.rent4 = rent4;
    }

    public int getRent5() {
        return rent5;
    }

    public void setRent5(int rent5) {
        this.rent5 = rent5;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getName() + " ");
        stringBuilder.append(getPosition() + " ");
        stringBuilder.append(getType() + " ");
        stringBuilder.append(getPrice() + " ");
        stringBuilder.append(getHousePrice() + " ");
        stringBuilder.append(getRent0() + " ");
        stringBuilder.append(getRent1() + " ");
        stringBuilder.append(getRent2() + " ");
        stringBuilder.append(getRent3() + " ");
        stringBuilder.append(getRent4() + " ");
        stringBuilder.append(getRent5() + " ");
        return stringBuilder.toString();
    }
    /*
    private String name;
    private int position;
    private String type;
    private int price;
    private int housePrice;
    private int rent0;
    private int rent1;
    private int rent2;
    private int rent3;
    private int rent4;
    private int rent5;

     */
}
