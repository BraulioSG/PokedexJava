package Logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import static java.lang.Integer.parseInt;

public class FileManager {

    public static void addToCSV(Pokemon pokemon, String path){
        try{
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.println(pokemon.name + "," + pokemon.type + "," + pokemon.num + 
                    "," + pokemon.img + "," + pokemon.url + "," + 
                    pokemon.rating);
            pw.flush();
            pw.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se exportaron los datos.");
        }
    }
    
    public static void write(String path){
        try{
            FileWriter fw = new FileWriter(path, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            String txt = "";
            int id = -1;
            
            for(Pokemon pokemon: Catalogo.pokemones){
                id++;
                txt += pokemon.name + "," + pokemon.type + "," + pokemon.num + 
                    "," + pokemon.img + "," + pokemon.url + "," + pokemon.rating;
                if(id != (Catalogo.pokemones.size()-1)){
                    txt += "\n";
                }
            }
            pw.println(txt);
            pw.flush();
            pw.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se exportaron los datos.");
        }
    }
    
    public static void read(String path){
        try{
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            
            String line = "";
            
            while((line = br.readLine()) != null){
                Pokemon poke = new Pokemon();
                String [] values = line.split(",");
                poke.name = values[0];
                poke.type = values[1];
                poke.num = values[2];
                poke.img = values[3];
                poke.url = values[4];
                poke.rating = parseInt(values[5]);
                Catalogo.pokemones.add(poke);
            }      
            br.close();  
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}