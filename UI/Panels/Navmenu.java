package UI.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Tools.*;
import UI.Windows.AddWindow;
import UI.Windows.HomeWindow;
import Components.Button;
import Logic.*;

public class Navmenu extends JPanel implements ActionListener{
    JLabel imgLabel;
    JPanel navmenuButtons;
    navButton addBtn, delBtn, findBtn;
    

    public Navmenu(){
        setLayout(new GridLayout(0,3));
        setBackground(Style.RED);
        initComponents();
    }

    private void initComponents(){

        //pokemon logo
        Image raw = ImageTool.getLocalImage("pokemon-banner.png"); //gets the imgae 
        ImageIcon img = new ImageIcon(
            ImageTool.resize(272, 100, raw)
        );
        imgLabel = new JLabel(img);
        this.add(imgLabel);


        //navMenubuttons
        addBtn = new navButton("Add");
        addBtn.addActionListener(this);
        delBtn = new navButton("Delete");
        delBtn.addActionListener(this);
        findBtn = new navButton("Find");
        findBtn.addActionListener(this);

        navmenuButtons = new JPanel(new GridLayout(0,3,10,0));
        navmenuButtons.add(addBtn);
        navmenuButtons.add(delBtn);
        navmenuButtons.add(findBtn);
        navmenuButtons.setBackground(Style.RED);
        
        add(new JLabel()); // empty label to move the buttons in the next cell in the grid layout
        add(navmenuButtons);

    }

    private class navButton extends Button{
        protected navButton(String text){
            super(text);
            setBackgroundColor(Style.RED);
            setForegroundColor(Style.WHITE);
            setHoverBackgroundColor(Style.WHITE);
            setHoverForegroundColor(Style.RED);
            setFont(Style.BOLD(20));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object target = e.getSource();
        if(target == addBtn){
            AddWindow win = new AddWindow();
            win.setVisible(true);
        }

        if(target == delBtn){
            Catalogo.pokemones.remove(Catalogo.pokemones.get(Catalogo.index));
            FileManager.write("./logs.csv");
            if(Catalogo.index >= Catalogo.pokemones.size())
                Catalogo.index = 0;
            HomeWindow.info.changePokemon(Catalogo.pokemones.get(Catalogo.index));
        }
        if(target == findBtn){
            String query = JOptionPane.showInputDialog("Pokemon to find:");
            int index = 0;
            boolean found = false;
            for(Pokemon p: Catalogo.pokemones){
                if(p.getName().toLowerCase().equalsIgnoreCase(query) &&  !found){
                    HomeWindow.info.changePokemon(Catalogo.pokemones.get(index));
                    found = true;
                }
                index++;
            }
            if(!found){
                JOptionPane.showMessageDialog(
                    null,
                    "NOT FOUND",
                    "ERROR",
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
