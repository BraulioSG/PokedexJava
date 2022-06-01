package Components;

import javax.swing.JTextField;
import java.awt.Cursor;
import java.awt.event.*;
import javax.swing.border.Border;

import Tools.Style;

import javax.swing.BorderFactory;

public class TextField extends JTextField implements Component{
    Border border = BorderFactory.createMatteBorder(0, 0, 3, 0, Style.GREY);
    boolean onFocus = false;
    public TextField(){
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(border);
        setBackground(Style.LIGHT_GREY);
        setFont(Style.PLAIN(15));
        
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt){
                if(!onFocus) setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Style.BLUE));
                
            }
            public void mouseExited(MouseEvent evt){
                if(!onFocus) setBorder(border);
                onFocus = false;
            }
        });

        addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Style.BLUE));
                setFont(Style.BOLD(18));
                onFocus = true;
            }

            @Override
            public void focusLost(FocusEvent e){
                setBorder(border);
                setFont(Style.PLAIN(15));
                onFocus = false;
            }
        });
        
    }

    @Override
    public void setup(){

    }

}