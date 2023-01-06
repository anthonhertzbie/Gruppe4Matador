package model;

import model.Field;

public class Gameboard {
    Field field = new Field();

    String [] fields = new String[40];

    public String [] getFields() {
        return fields;
    }

    public void createGameboard (){
        for (int i = 0; i < field.length; i++) {
            fields[i] = field.getField("_Fields", i);
        }
    }
}
