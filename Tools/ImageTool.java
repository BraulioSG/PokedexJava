package Tools;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;

public class ImageTool {
    
    public static Image getLocalImage(String filename){
        ImageIcon img = new ImageIcon(ImageTool.class.getResource("../assets/imgs/" + filename));
        return img.getImage();
    }

    //Image methods
    public static Image resize(int width, int height, Image file){
        Image resized = file.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return resized;
    }

    public static ImageIcon resize(int width, int height, ImageIcon file){
        Image temp = file.getImage();
        ImageIcon resized = new ImageIcon(resize(width, height, temp));

        return resized;
    }

    public static Image imageFromPath(String path){
        File file = new File(path);
        Image rawImage = null;
        try{
            rawImage = ImageIO.read(file);
            return rawImage;
        }
        catch(IOException error){
            return null; //no image
        }
    }
}
