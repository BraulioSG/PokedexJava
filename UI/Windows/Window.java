package UI.Windows;

import javax.swing.JFrame;
import Tools.*;

public class Window extends JFrame{
    protected int width, height;

    public Window(int width, int height){
        this.width = width;
        this.height = height;
        initWindow();
    }

    private void initWindow(){
        setBounds(0,0, width, height);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Style.WHITE);
        setIconImage(ImageTool.getLocalImage("app-icon.png"));
    }
}
