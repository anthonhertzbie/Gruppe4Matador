package org.example;

public class Field {
    String field;

    Helper helper = new Helper();

    public String getField(String File, int lineNo){
        field = helper.(File, lineNo);
        return field;
    }
}
