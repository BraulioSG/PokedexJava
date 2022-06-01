package Logic;

import UI.Windows.*;

public class App {
    public static void main(String[] args) {
        try{
            FileManager.read("./logs.csv");
        }catch(Exception e){}
        HomeWindow win = new HomeWindow();
        win.setVisible(true);

    }


}
