package org.example;

import java.io.*;

public class Helper {
    String[][] fieldData = new String[41][11];
    String[] chancecards = new String[45];

    /**
     * Constructor for the Helper class that reads the txt and csv files in the resource folder
     */
    public Helper() throws IOException {
        csvReader();
        txtReader();
    }

    private void txtReader(){
        try (InputStream in = getClass().getResourceAsStream("/chancecards.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            for (int i = 0; i < 45; i++) {
                chancecards[i] = reader.readLine();
            }
            for (int i = 0; i < chancecards.length; i++) {
                System.out.println(chancecards[i]);
            }
            // Use resource
        } catch (IOException e){
            e.printStackTrace();
        }


    }
    private void csvReader(){
        //https://stackoverflow.com/questions/20389255/reading-a-resource-file-from-within-jar
        try (InputStream in = getClass().getResourceAsStream("/fields.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            int i = 0;
            while((line = reader.readLine()) != null){
                String[] datastoreage = line.split(",");
                for (int j = 0; j < datastoreage.length ; j++) {
                    fieldData[i][j] = datastoreage[j];
                }
                i++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getFieldData(int row, int colums) {
        return fieldData[row][colums];
    }

    public String getChancecards(int lineNo){
        return chancecards[lineNo];
    }


    public static void main(String[] args) throws IOException {
        Helper helper = new Helper();
        helper.chancecardsPrint();
        helper.fieldsPrint();

    }

    public void fieldsPrint(){
        for (int i = 0; i < 41; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(fieldData[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public void chancecardsPrint(){
        for (int i = 0; i < chancecards.length; i++) {
            System.out.println(chancecards[i]);
        }
    }

}
