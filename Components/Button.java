package Components;
import javax.swing.*;

import Tools.Style;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Cursor;

public class Button extends JButton implements Component{

        private Color bgcolor = Style.BLUE;
        private Color bgcolorhover = Style.DARK_BLUE;
        private Color fgcolor = Style.YELLOW;
        private Color fgcolorhover = Style.YELLOW;

        public Button(String text){
            this.setup();
            setText(text);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt){
                    onHover();
                }
                public void mouseExited(MouseEvent evt){
                    offHover();
                }
            });

        }

        @Override
        public void setup(){
            setBackground(this.bgcolor);
            setForeground(this.fgcolor);
            setBorderPainted(false);
            setFocusPainted(false);
            setFont(Style.BOLD(15));
            
        }

        public void onHover(){
            setBackground(bgcolorhover);
            setForeground(fgcolorhover);
        }

        public void offHover(){
            setBackground(bgcolor);
            setForeground(fgcolor);
        }

        //setters
        public void setBackgroundColor(Color newColor){
            this.bgcolor = newColor;
            this.setup();
        }

        public void setHoverBackgroundColor(Color newColor){
            this.bgcolorhover = newColor;
            this.setup();
        }
        public void setForegroundColor(Color newColor){
            this.fgcolor = newColor;
            this.setup();
        }

        public void setHoverForegroundColor(Color newColor){
            this.fgcolorhover = newColor;
            this.setup();
        }
        
    
}
