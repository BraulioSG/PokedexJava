package Logic;

import java.util.ArrayList;

public class Catalogo {
    public static ArrayList<Pokemon> pokemones = new ArrayList<Pokemon>();
    public static int index = 0;
    
    public static void printCatalogo(){
        for(Pokemon poke:pokemones){
            System.out.println("---------------------" + poke.toString());
        }
    }
}