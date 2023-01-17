package model;

import java.io.*;

public class Helper {
    String[][] fieldData = new String[41][11];
    String[] chancecards = new String[45];
    String[][] carColour = new String[6][5];

    /**
     * Constructor for the Helper class that reads the txt and csv files in the resource folder
     */
    public Helper() {
            csvReader();
            txtReader();
            carColourReader();
    }

    public void txtReader(){
        try (InputStream in = getClass().getResourceAsStream("/chancecards.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            for (int i = 0; i < 45; i++) {
                chancecards[i] = reader.readLine();
            }
            /*
            for (int i = 0; i < chancecards.length; i++) {
                System.out.println(chancecards[i]);
            }
             */
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
    private void carColourReader(){
        //https://stackoverflow.com/questions/20389255/reading-a-resource-file-from-within-jar
        try (InputStream in = getClass().getResourceAsStream("/Car-colour.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            int i = 0;
            while((line = reader.readLine()) != null){
                String[] datastoreage = line.split("/");
                for (int j = 0; j < datastoreage.length ; j++) {
                    carColour[i][j] = datastoreage[j];
                }
                i++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public int getCarColour(int row, int colums) {return Integer.parseInt(carColour[row][colums]);}

    public String getFieldData(int row, int colums) {
        return fieldData[row][colums];
    }

    public String getChancecards(int lineNo){
        return chancecards[lineNo];
    }




}
