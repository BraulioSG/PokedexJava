package Tools;

import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;

public class Style {
    //color palette
    public static final Color RED = new Color(204,0,0);
    public static final Color BLUE = new Color(0,117,190);
    public static final Color YELLOW = new Color(255,204,0);
    public static final Color DARK_BLUE = new Color(10,40,95);
    public static final Color DARK_YELLOW = new Color(213, 161,0);

    //gray scale tones
    public static final Color WHITE = new Color(255,255,255);
    public static final Color LIGHT_GREY = new Color(245, 245, 245);
    public static final Color GREY = new Color(220,220,220);
    public static final Color BLACK = new Color(0,0,0);
    
    //fonts
    public static Font PLAIN(int size){
        return new Font(Font.SANS_SERIF, Font.PLAIN, size);
    }
    public static Font BOLD(int size){
        return new Font(Font.SANS_SERIF, Font.BOLD, size);
    }
    public static Font ITALIC(int size){
        return new Font(Font.SANS_SERIF, Font.ITALIC, size);
    }

    //pokemon font
    public static Font POKEFONT(int size) throws Exception{
        String fName = "../assets/fonts/poke-font.ttf";
        InputStream is = Style.class.getResourceAsStream(fName);
        Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        
        return font.deriveFont(Font.TRUETYPE_FONT,size);
    }
}
