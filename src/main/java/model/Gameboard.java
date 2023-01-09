package model;

public class Gameboard {
    private Helper helper = new Helper();
    private Field[] fields = new Field[41];
    private int[][] prices = new int[41][8];

    public Gameboard(){
        copyFieldInformation();
    }




    private void copyFieldInformation(){
        copyTitels();
        copyNumbers();


        for (int i = 0; i < 40; i++) {
            System.out.print(fields[i].getTitleOf());
            for (int j = 0; j < 8; j++) {
                System.out.print(" " + prices[i][j]);
            }
            System.out.println();
        }
    }
    private void copyTitels(){
        for (int i = 0; i < 40; i++) {
            fields[i] = new Field(helper.getFieldData(i + 1, 0));
            System.out.println(helper.getFieldData(i + 1, 0));
        }
    }
    private void copyNumbers(){
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 8; j++) {
                prices[i][j] = Integer.parseInt(helper.getFieldData(i + 1, j + 3));
                System.out.println(helper.getFieldData(i + 1, j + 3));
            }
        }
    }
}
