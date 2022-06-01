package UI.Panels;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

import Components.Button;
import Tools.ImageTool;
import Tools.Style;
import Logic.FileManager;
import Logic.Pokemon;
public class Rating extends JPanel implements ActionListener{
    public int score = 0;
    private int size = 50;
    ArrayList<StarBtn> stars = new ArrayList<>();
    Pokemon pokemon;

    public Rating(Pokemon pokemon){
        score = 0;
        size = 50;
        setLayout(new GridLayout(0,5));
        setSize(250,50);
        initComponents();
        showCurrentRating();
    }

    public Rating(Pokemon pokemon, int size){
        this.size = size;
        setLayout(new GridLayout(0,5));
        setSize(250,50);
        initComponents();
        showCurrentRating();
    }

    public void initComponents(){
        
        for(int i = 0; i < 5; i++){
            stars.add(new StarBtn(i+1, this));
        }

        int idx = 0;
        for(StarBtn s: stars){
            s.addActionListener(this);
            s.setBounds(idx * 50, 0, 50, 50);
            idx++;
            add(s);
        }
        
    }

    public void changeRating(int newRating){
        int idx = 0;
        for(StarBtn s : stars){
            s.active = idx < newRating;
            idx++;
            s.showIcon();            
        }
    }

    public void showCurrentRating(){
        boolean onHover = false;
        for(StarBtn s : stars){
            if(s.hover){
                onHover = true;
            }
        }
        if(!onHover){
            int idx = 0;
            for(StarBtn s: stars){
                s.active = (idx < score);
                idx++;
                s.showIcon();
            }
        }
    }

    public void updateRating(int rating){
        this.score = rating;
        pokemon.setRating(this.score);
    }

    public void changePokemon(Pokemon pokemon){
        try{
            this.pokemon = pokemon;
            this.score = pokemon.getRating();
        }catch(Exception e){}
        
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object target = e.getSource();
        int idx = 1;
        for(StarBtn s : stars){
            if(target == s){
                updateRating(idx);
                FileManager.write("./logs.csv");
            }
            idx++;
        }
    }

    private class StarBtn extends Button{
        public boolean active;
        private boolean hover = false;
        private int id;

        public StarBtn(int id, Rating conteiner){
            super("");
            this.id = id;
            showIcon();
            setBackgroundColor(Style.WHITE);
        }

        

        @Override
        public void onHover(){
            hover = true;
            changeRating(id);
        }

        @Override
        public void offHover(){
            hover = false;
            showCurrentRating();
        }

        public void showIcon(){
            
            if(!active){
                setIcon(new ImageIcon(
                    ImageTool.resize(size, size, 
                        ImageTool.getLocalImage("star-empty.png")
                    )
                ));
            }
            else{
                setIcon(new ImageIcon(
                    ImageTool.resize(size, size,
                        ImageTool.getLocalImage("star-filled.png")
                    )
                ));
            }

        }
    }
}
