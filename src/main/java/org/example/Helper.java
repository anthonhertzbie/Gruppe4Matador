package org.example;

import java.io.*;

public class Helper {
    String[][] fieldData = new String[41][11];
    String[] chancecards = new String[45];

    public void txtReader() throws IOException {
        String[] txt = new String[45];
        try (InputStream in = getClass().getResourceAsStream("/chancecards.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            for (int i = 0; i < 45; i++) {
                txt[i] = reader.readLine();
            }
            for (int i = 0; i < txt.length; i++) {
                System.out.println(txt[i]);
            }
            // Use resource
        } catch (IOException e){
            e.printStackTrace();
        }
        /*
        try {
            BufferedReader reader = new BufferedReader(new FileReader("chancecards.txt"));
            txt = reader.readLine();
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }

         */


    }
    public void csvReader(){
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

            // Use resource
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Helper helper = new Helper();
        //helper.txtReader();
        helper.csvReader();
        helper.print();

    }

    public void print(){
        for (int i = 0; i < 41; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(fieldData[i][j] + ", ");
            }
            System.out.println();
        }
    }

}
