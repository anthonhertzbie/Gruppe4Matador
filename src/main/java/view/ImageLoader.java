package view;

import model.Gameboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    private BufferedImage bufferedImage;

    public ImageLoader(){
        JLabel dlabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img.png")));
        try{

            System.out.println("im here");
            System.out.println(bufferedImage + " Still in the test");
            bufferedImage = ImageIO.read(getClass().getClassLoader().getResource("img.png"));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public BufferedImage getBufferedImage(){
        System.out.println("im here 2");
        return bufferedImage;
    }



    public static void main(String[] args) throws IOException {

    }
}
