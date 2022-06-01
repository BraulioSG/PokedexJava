package UI.Panels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import Components.Button;
import Logic.Pokemon;
import Tools.*;

public class Pokeinfo extends JPanel{
    JLabel image;
    Pokedata data;
    Rating rating;
    Button nextBtn, prevBtn; 

    boolean blue, rigth;

    public Pokeinfo(){
        setSize(1000,500);
        setLayout(new GridLayout(0,2));
        setBackground(Style.WHITE);
        //setBackground(Style.BLUE);
        initComponents();

    }

    private void initComponents(){
        image = new JLabel(
            new ImageIcon(
                ImageTool.resize(getWidth()/2, getHeight(), 
                    ImageTool.getLocalImage("missing-file.png")
                )
            )
        );

        image.setBackground(Style.RED);
        add(image);

        data = new Pokedata(new Pokemon(), this);
        add(data);

        

    }

    public void changePokemon(Pokemon pokemon){
        data.changePokemon(pokemon);
    }

    public void changeImage(String path){
        image.setIcon(
            new ImageIcon(
                ImageTool.resize(getWidth()/2, getHeight(), 
                    ImageTool.imageFromPath(path)
                )
            )
        );
    }


}
