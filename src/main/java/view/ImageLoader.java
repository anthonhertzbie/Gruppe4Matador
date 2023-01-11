package view;

import model.Gameboard;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    private BufferedImage bufferedImage;

    public ImageLoader(){
        try{
            System.out.println("im here");
            System.out.println(bufferedImage + " Still in the test");
            bufferedImage = ImageIO.read(getClass().getResourceAsStream("/resources/img.png"));
            System.out.println(bufferedImage);
        }catch (Exception e){}
    }

    public BufferedImage getBufferedImage(){
        System.out.println("im here 2");
        return bufferedImage;
    }



    public static void main(String[] args) throws IOException {

    }
}
