package view;

public class viewMessages extends View{
    private Integer choosePlayerAmount(){
        return Integer.parseInt(gui.getUserSelection("Welcome, please choose the number of players : ", "3", "4", "5","6"));
    }
    private String nameOfPlayer(int playerAmount, int currentPlayer,String playerName){
        return "hi";
    }
}
