import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Sphere;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aabidi & Ezzahri
 */
public class TheShapes {
    public ToggleButton carbtn1, linebtn1, tri, circlebtn1, rect, elip, sphe, cylin, box, polyline, threD;
    public VBox btns1, btns2;
    public ToggleGroup tools1;
    
    public TheShapes(){
       
        Circle c1=new Circle(30);
        Line l1 = new Line(1, 1, 60, 60);
        Rectangle r1 = new Rectangle(60, 60);
        Rectangle r2 = new Rectangle(70, 50);
        Arc a1 = new Arc(0.0f, 0.0f, 80.0f, 60.0f, 0.0f, 75.0f);
        Polygon p1 = new Polygon(40,10,10,70,70,70);
        Ellipse e1 = new Ellipse(33, 20);
        Polyline p2 = new Polyline(15,3 , 55,3 , 67,25 , 55,48 , 15,48 , 3,25 , 15,3);
        Sphere s1 = new Sphere(30);
        Cylinder c2 = new Cylinder(20, 60);
        Box b1 = new Box(60, 50, 20);
        
        
        
        
        Shape [] allShapes = {r2,p1,e1,c1,r1,p2};
        for (Shape sh : allShapes){
            sh.setFill(Color.TRANSPARENT);
            sh.setStroke(Color.BLACK);
        }
        
        this.carbtn1 = new ToggleButton("",r1);
        this.linebtn1 = new ToggleButton("",c1);
        this.tri = new ToggleButton("",p1);
        this.circlebtn1 = new ToggleButton("",r2);
        this.rect = new ToggleButton("",l1);
        this.elip = new ToggleButton("",e1);
        this.sphe = new ToggleButton("",s1);
        this.cylin = new ToggleButton("", c2);
        this.box = new ToggleButton("", b1);
        this.polyline = new ToggleButton("", p2);
        this.threD = new ToggleButton("Draw 3D");
        
        ToggleButton[] tls1 = { carbtn1, linebtn1, tri, circlebtn1,rect ,elip, sphe, cylin, box, polyline, threD};
        this.tools1 = new ToggleGroup();    
       for (ToggleButton tool : tls1) {
            tool.setMinWidth(80);
            tool.setMinHeight(80);
            tool.setToggleGroup(tools1);
            tool.setCursor(Cursor.HAND);  
        }
        
        this.btns1 = new VBox(7);
        btns1.getChildren().addAll(carbtn1, linebtn1, tri, circlebtn1, rect, elip, polyline);
        btns1.setPadding(new Insets(4));
        btns1.setPrefWidth(80);
        btns1.setLayoutX(10);
        btns1.setLayoutY(50);
        
        this.btns2 = new VBox(10);
        btns2.getChildren().addAll(sphe, cylin, box, threD);
        btns2.setPadding(new Insets(4));
        btns2.setPrefWidth(80);
    }
}