import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aabidi & Ezzahri
 */
public class TopMenu2 {
    public Button carbtn, linebtn, rectbtn, neew, close/*, text*/, threeD, twoD;
    public ToggleButton  gomme, pen;
    public Label line_color, fill_color;
    public ColorPicker cpLine, cpFill;
    public Slider slider;
    public HBox btns3;
    public ToggleButton [] tbtns;
    
    public TopMenu2(){
        
        /*--------------------------------------------- Images ------------------------------------------------------*/
        
        ImageView undo = new ImageView(new Image(getClass().getResourceAsStream("imgs/unnamed.png")));
        ImageView redo = new ImageView(new Image(getClass().getResourceAsStream("imgs/Redo-icon.png")));
        ImageView sauv = new ImageView(new Image(getClass().getResourceAsStream("imgs/save.png")));
        ImageView pencil = new ImageView(new Image(getClass().getResourceAsStream("imgs/pencil.png")));
        ImageView gom = new ImageView(new Image(getClass().getResourceAsStream("imgs/gomme.png")));
        ImageView neww = new ImageView(new Image(getClass().getResourceAsStream("imgs/new.png")));
        ImageView exit = new ImageView(new Image(getClass().getResourceAsStream("imgs/close.png")));
        ImageView deuxD = new ImageView(new Image(getClass().getResourceAsStream("imgs/2d.png")));
        ImageView troisD = new ImageView(new Image(getClass().getResourceAsStream("imgs/3d.png")));
        
        ImageView [] images = {undo, redo, sauv, pencil,gom, neww, exit, deuxD, troisD};
        for(ImageView img : images){
            img.setFitHeight(20);
            img.setFitWidth(20);
        }

        /*----------------------------------------------------- bar ------------------------------------------------------------------*/

        this.carbtn = new Button("",undo);
        this.linebtn = new Button("",redo);
        this.rectbtn = new Button("",sauv);
        this.close = new Button("",exit);
        this.pen = new ToggleButton("",pencil);
        this.neew = new Button("",neww);
        this.gomme = new ToggleButton("",gom);
        //this.text = new Button("T");
        this.slider = new Slider(1, 20, 0.5);
        this.threeD = new Button("", troisD);
        this.twoD = new Button("", deuxD);
        
        ToggleButton [] tbtns = {gomme, pen};
        
        for(ToggleButton tbtn :tbtns){
            tbtn.setMinHeight(30);
            tbtn.setMinWidth(30);
            tbtn.setCursor(Cursor.HAND);
        }
        
        Button [] btns = {carbtn, linebtn, rectbtn, neew, close/*, text*/, threeD, twoD};
        for (Button btn : btns){
            btn.setMinHeight(30);
            btn.setMinWidth(30);
            btn.setCursor(Cursor.HAND);
        }
        
        this.line_color = new Label("Line Color : ");
        this.fill_color = new Label("Fill Color : ");
        this.line_color.setMaxHeight(50);
        this.fill_color.setMaxHeight(40);
        
        
        this.cpLine = new ColorPicker(Color.BLACK);
        this.cpFill = new ColorPicker(Color.WHITE);


        this.cpFill.setCursor(Cursor.HAND);
        this.cpLine.setCursor(Cursor.HAND);
        
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setCursor(Cursor.HAND);
        slider.setValue(8);

        this.btns3 = new HBox(1);
        btns3.setPadding(new Insets(5,0,10,200));
        btns3.getChildren().addAll(neew, rectbtn, close, carbtn, linebtn/*, text*/, pen, gomme, twoD, threeD, slider, new Separator(Orientation.HORIZONTAL), line_color,
                cpLine, new Separator(Orientation.HORIZONTAL), fill_color, cpFill);
        btns3.setPrefHeight(30);
    }
}
