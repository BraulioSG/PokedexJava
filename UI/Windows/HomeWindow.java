package UI.Windows;


import javax.swing.JFrame;
import Logic.Catalogo;
import UI.Panels.*;


public class HomeWindow extends Window implements WindowInterface{
    Navmenu nav;
    public static Pokeinfo info;

    public HomeWindow(){
        super(1280, 800);
        setTitle("Pokedex");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    @Override
    public void initComponents(){
        nav = new Navmenu();
        nav.setBounds(0,0,this.width,100);
        add(nav);

        info = new Pokeinfo();
        info.setLocation(
            getWidth() / 2 - (info.getWidth() / 2),
            150
            );
        add(info);

        if(Catalogo.pokemones.size() > 0 )
            info.changePokemon(Catalogo.pokemones.get(0));

    }
    
}
