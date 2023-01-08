package model;

import model.Field;

public class Gameboard {
    Helper helper = new Helper();

    Field[] fields = new Field[40];

    public Gameboard(){
        for (int i = 1; i < fields.length; i++) {
            fields[i] = new Field();
            fields[i].setName(helper.getFieldData(i,0));
            fields[i].setPosition(Integer.parseInt(helper.getFieldData(i,1)));
            fields[i].setType(helper.getFieldData(i, 2));
            fields[i].setPrice(Integer.parseInt(helper.getFieldData(i, 3)));
            fields[i].setHousePrice(Integer.parseInt(helper.getFieldData(i, 4)));
            fields[i].setRent0(Integer.parseInt(helper.getFieldData(i, 5)));
            fields[i].setRent1(Integer.parseInt(helper.getFieldData(i, 6)));
            fields[i].setRent2(Integer.parseInt(helper.getFieldData(i, 7)));
            fields[i].setRent3(Integer.parseInt(helper.getFieldData(i, 8)));
            fields[i].setRent4(Integer.parseInt(helper.getFieldData(i, 9)));
            fields[i].setRent5(Integer.parseInt(helper.getFieldData(i, 10)));


        }
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].toString());

        }


    }

}
