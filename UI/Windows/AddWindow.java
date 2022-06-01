package UI.Windows;

import java.awt.event.*;
import java.io.File;
import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import Tools.*;
import Components.Button;
import Components.TextField;
import Logic.Catalogo;
import Logic.FileManager;
import Logic.Pokemon;
import UI.Panels.Rating;

public class AddWindow extends Window implements WindowInterface, ActionListener{

    
    private JLabel title, img;
    Pokemon pokemon = new Pokemon();

    //fomr6s component
    private Button upload;
    private JFileChooser fileChooser;
    private JPanel form, buttons;
    private Label imageLabel, nameLabel, typeLabel, urlLabel, numLabel; //lables
    private JLabel imagePreview;
    private JTextField imagePathTf;
    private TextField nameField, typeField, urlField, numField;
    private Rating rating;
    private Button okBtn, cancelBtn;

    public AddWindow(){
        super(700,600);
        initComponents();
    }

    @Override
    public void initComponents(){
       

        img = new JLabel();
        img.setBounds(50,50,100,100);
        add(img);

        title = new JLabel("Add a Pokemon");
        title.setBounds(0,0,this.width, 50);
        title.setOpaque(true);
        title.setBackground(Style.RED);
        try{
            title.setFont(Style.POKEFONT(25));
            title.setForeground(Style.YELLOW);
            title.setHorizontalAlignment(JLabel.CENTER);
        }catch(Exception e){
            System.out.println("Error with font");
        }
        
        add(title);

        fileChooser = new JFileChooser();

        imageLabel = new Label("Image");
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setFont(Style.BOLD(20));
        imageLabel.setBounds(10, 65, 330, 30);
        add(imageLabel);

        upload = new Button("upload");
        upload.setBounds(10,100,100,30);
        upload.addActionListener(this);
        add(upload);

        imagePathTf = new JTextField();
        imagePathTf.setBounds(115, 100, 227, 30);
        imagePathTf.setEditable(false);
        imagePathTf.setBackground(Style.GREY);
        imagePathTf.setBorder(null);
        imagePathTf.setForeground(Style.BLACK);
        add(imagePathTf);

        imagePreview = new JLabel(new ImageIcon("./assets/missing-file.png"));
        imagePreview.setBounds(10, 150, 330, 330);
        imagePreview.setHorizontalAlignment(JLabel.CENTER);
        imagePreview.setBackground(Style.GREY);
        imagePreview.setOpaque(true);
        add(imagePreview);

        form = new JPanel();
        form.setLayout(new GridLayout(12,0, 0, 3));
        form.setBackground(Style.WHITE);
        form.setBounds(365, 65, 300, 350);

        nameLabel = new Label("Name");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(Style.BOLD(20));
        form.add(nameLabel);        
        nameField = new TextField();
        nameField.setHorizontalAlignment(JTextField.CENTER);
        form.add(nameField);
        form.add(new JLabel()); //empty label to move the grid

        typeLabel = new Label("Type");
        typeLabel.setHorizontalAlignment(JLabel.CENTER);
        typeLabel.setFont(Style.BOLD(20));
        form.add(typeLabel);        
        typeField = new TextField();
        typeField.setHorizontalAlignment(JLabel.CENTER);
        form.add(typeField);
        form.add(new JLabel()); //empty label to move the grid

        urlLabel = new Label("URL");
        urlLabel.setHorizontalAlignment(JLabel.CENTER);
        urlLabel.setFont(Style.BOLD(20));
        form.add(urlLabel);        
        urlField = new TextField();
        urlField.setHorizontalAlignment(JLabel.CENTER);
        form.add(urlField);
        form.add(new JLabel()); //empty label to move the grid

        numLabel = new Label("Pokedex Num");
        numLabel.setHorizontalAlignment(JLabel.CENTER);
        numLabel.setFont(Style.BOLD(20));
        form.add(numLabel);        
        numField = new TextField();
        numField.setHorizontalAlignment(JLabel.CENTER);
        form.add(numField);

        rating = new Rating(pokemon, 30);
        form.add(rating);
        add(form);
        buttons = new JPanel();
        buttons.setLayout(new GridLayout(0,2,25,0));
        buttons.setBackground(Style.WHITE);
        buttons.setBounds(365,440,300,40);
        okBtn = new Button("OK");
        okBtn.addActionListener(this);
        buttons.add(okBtn);

        cancelBtn = new Button("Cancel");
        cancelBtn.addActionListener(this);
        buttons.add(cancelBtn);
        add(buttons);
    }

    

    private static class Label extends JLabel{
        public Label(String text){
            setText(text);
            setFont(Style.PLAIN(18));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object target = e.getSource();
        if(target == upload){
            int returnValue = fileChooser.showOpenDialog(this);
            if(returnValue == JFileChooser.APPROVE_OPTION){
                File f = fileChooser.getSelectedFile();
                
                boolean validFile = false;
                String fileName = f.getName();
                if(fileName.endsWith(".jpg")) validFile = true;
                if(fileName.endsWith(".jpeg")) validFile = true;
                if(fileName.endsWith(".png")) validFile = true;

                if(validFile){
                    String path = f.getPath().replace("\\","/");
                    Image rawImg = ImageTool.imageFromPath(path);

                    if(rawImg != null) imagePreview.setIcon(
                        ImageTool.resize(330, 330, new ImageIcon(rawImg))
                    );
                    imagePathTf.setText(path);           
                }

            }
            else{
                System.out.println("something went wrong");
            }
        }
        else if (target == cancelBtn){
            this.dispose();
        }
        else if (target == okBtn){
            Border wrong = BorderFactory.createMatteBorder(0,0, 3, 0, Style.RED);
            boolean valid = true;
            if(nameField.getText().isEmpty()){
                nameField.setBorder(wrong);
                valid = false;
            }
            if(typeField.getText().isEmpty()){
                typeField.setBorder(wrong);
                valid = false;
            }
            if(urlField.getText().isEmpty()){
                urlField.setBorder(wrong);
                valid = false;
            }
            if(imagePathTf.getText().isEmpty()){
                valid = false;
            }

            if(valid){
                pokemon.setImg(imagePathTf.getText());
                pokemon.setName(nameField.getText());
                pokemon.setType(typeField.getText());
                pokemon.setURL(urlField.getText());
                pokemon.setNum(numField.getText());
                pokemon.setRating(rating.score);
                FileManager.addToCSV(pokemon, "./logs.csv");
                Catalogo.pokemones.add(pokemon);
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(
                    null,
                    "missing information",
                    "Incomplete Form",
                    JOptionPane.WARNING_MESSAGE);
            }

        }
    }
}
