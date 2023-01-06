package model;

import model.Helper;

public class Field {
    String field;

    Helper helper = new Helper();

    public String getField(int row, int column){
        field = helper.getFieldData(row, column);
        return field;
    }
}
