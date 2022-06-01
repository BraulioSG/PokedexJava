package UI.Panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import Components.Button;
import Logic.Catalogo;
import Logic.Pokemon;
import Tools.Style;
import java.awt.*;
import java.awt.event.*;
import java.awt.Desktop;
import java.net.URI;


public class Pokedata extends JPanel implements ActionListener{
    Rating rating;
    Pokemon pokemon;
    JLabel name, type, num;
    Button urlBtn, nextBtn, prevBtn;
    private Pokeinfo container;

    public Pokedata(Pokemon pokemon, Pokeinfo container){
        this.container = container;
        setLayout(new GridLayout(7,0,10,10));
        this.pokemon = pokemon;
        setBackground(Style.WHITE);
        initComponents();
    }   

    private void initComponents(){
        name = new JLabel(" Name: " + pokemon.getName());
        type = new JLabel(" Type: " + pokemon.getType());
        num = new JLabel(" Num: " + pokemon.getNum());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(100,5));
        panel.add(new JLabel(" "),BorderLayout.WEST);
        panel.add(new JLabel(" "),BorderLayout.NORTH);
        urlBtn = new Button("More Info");
        urlBtn.addActionListener(this);
        panel.add(urlBtn, BorderLayout.CENTER);
        panel.add(new JLabel(" "), BorderLayout.EAST);
        panel.setBackground(Style.WHITE);
        
        JPanel buttons = new JPanel();
        nextBtn = new Button("Next");
        nextBtn.addActionListener(this);
        prevBtn = new Button("Prev");
        prevBtn.addActionListener(this);
        buttons.setLayout(new GridLayout(0,2, 10, 0));
        
        buttons.add(prevBtn);
        buttons.add(nextBtn);

        try{
            int size = 30;
            name.setFont(Style.BOLD(size));
            type.setFont(Style.BOLD(size));
            num.setFont(Style.BOLD(size));
            urlBtn.setFont(Style.BOLD(size));
            nextBtn.setFont(Style.BOLD(size));
            prevBtn.setFont(Style.BOLD(size));
        }catch(Exception e){
            System.out.println("error");
        }
        
        rating = new Rating(pokemon);

        add(name);
        add(type);
        add(num);
        add(panel);
        add(rating);
        add(new JLabel());
        add(buttons);
    }

    public void changePokemon(Pokemon pokemon){
        this.pokemon = pokemon;
        rating.changePokemon(pokemon);
        rating.showCurrentRating();
        name.setText(" Name: " + pokemon.getName());
        type.setText(" Type: " + pokemon.getType());
        num.setText(" Num: " + pokemon.getNum());
        container.changeImage(pokemon.getImg());
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object target = e.getSource();
        if(target == urlBtn){
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try{
                    Desktop.getDesktop().browse(new URI(pokemon.getURL()));
                }catch(Exception error){
                    System.out.println(error);
                }
                
            }
        }
        if(target == nextBtn){
            Catalogo.index++;
            if(Catalogo.index >= Catalogo.pokemones.size())
                Catalogo.index = 0;
            container.changePokemon(Catalogo.pokemones.get(Catalogo.index));
        }
        if(target == prevBtn){
            Catalogo.index--;
            if(Catalogo.index < 0)
                Catalogo.index = Catalogo.pokemones.size() - 1;
            container.changePokemon(Catalogo.pokemones.get(Catalogo.index));
        }
    }
}
