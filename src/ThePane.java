import java.util.Stack;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.Box;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aabidi & Ezzahri
 */
public class ThePane {
    public Pane panee;
    public final Stack <Shape> undoHistory = new Stack();
    public final Stack <Shape> redoHistory = new Stack();
    public final Stack<Shape3D> undoHistory3D = new Stack();
    public final Stack<Shape3D> redoHistory3D = new Stack();
    public double x,y;
//    public final Stack<Text> allText = new Stack();
//    private TextArea t;
//    private Text tt;
//    private Button b;
//    private HBox v;

    public ThePane() {
        this.panee = new Pane();
        this.panee.setStyle("-fx-background-color: #FFFFFF");
        this.panee.setMaxHeight(630);
        this.panee.setMaxWidth(1180);
        this.panee.setCursor(Cursor.CROSSHAIR);
    }

    public void addCircle(Color fill , Color line , double x , double y, double xx, double yy){
        double rayon = (Math.pow((xx-x),2)+Math.pow((yy-y),2)); 
        double r = Math.sqrt(rayon)/2;
        Circle c = new Circle(x+r, y+r, r, fill);
        c.setStroke(line);
        this.panee.getChildren().add(c);
        undoHistory.push(c);
    }

    public void addRectangle(Color fill , Color line , double x , double y, double xx){
        Rectangle r = new Rectangle(xx-x, xx-x, fill);
        r.setStroke(line);
        r.setX(x);
        r.setY(y);
        panee.getChildren().add(r);
        undoHistory.push(r);
    }
    
    public void addBigRectangle(Color fill , Color line , double x , double y, double xx, double yy){
        Rectangle r = new Rectangle(xx-x, yy-y, fill);
        r.setStroke(line);
        r.setX(x);
        r.setY(y);
        panee.getChildren().add(r);
        undoHistory.push(r);
    }

    public void addEllipse(Color fill , Color line , double x , double y, double xx, double yy){
        double cx = (xx-x)/2;
        double cy = (yy-y)/2;
            Ellipse e = new Ellipse(cx, cy);
            e.setCenterX(x+cx);
            e.setCenterY(y+cy);
            e.setFill(fill);
            e.setStroke(line);
            panee.getChildren().add(e);
            undoHistory.push(e);
    }
    
    public void addLine(Color line , double x , double y, double xx, double yy){
            Line l1 = new Line(x, y, xx, yy);
            l1.setStroke(line);
            panee.getChildren().add(l1);
            undoHistory.push(l1);
    }
    
    public void addTriangle(Color fill , Color line , double x , double y, double xx, double yy){
            Polygon p = new Polygon(x, y, x-(xx-x), yy, xx, yy);
            p.setFill(fill);
            p.setStroke(line);
            panee.getChildren().add(p);
            undoHistory.push(p);
    }
    
    public void undo(Boolean b){
        if(!undoHistory.empty() || !undoHistory3D.empty()){
            if(b == true){
                Shape removedShape = undoHistory.lastElement();
                redoHistory.push(removedShape);
                this.panee.getChildren().remove(removedShape);
                undoHistory.remove(undoHistory.lastElement());
            }
            else if(b == false){
                Shape3D removedShape = undoHistory3D.lastElement();
                redoHistory3D.push(removedShape);
                this.panee.getChildren().remove(removedShape);
                undoHistory3D.remove(undoHistory3D.lastElement());
            }
        }
        else {
            System.err.println("Nothing to undo !!!");
        }
        
    }
    
    public void redo(Boolean b){
        if(!redoHistory.empty() || !redoHistory3D.empty()){
            if(b == true){
                Shape releaseShape = redoHistory.lastElement();
                undoHistory.push(releaseShape);
                this.panee.getChildren().add(releaseShape);
                redoHistory.remove(redoHistory.lastElement());
            }
            else if(b == false){
                Shape3D releaseShape = redoHistory3D.lastElement();
                undoHistory3D.push(releaseShape);
                this.panee.getChildren().add(releaseShape);
                redoHistory3D.remove(redoHistory3D.lastElement());
            }
        }
        else {
            System.err.println("Nothing to redo !!!");
        }
    }

    public void pen(double x, double y, double p, Color fill){
        Circle c = new Circle(x, y, p, fill);
        c.setStroke(fill);
        //undoHistory.push(c);
        this.panee.getChildren().add(c);
    }
    
    public void draw3d(double x, double y, Color fill){
        Path path = new Path();
        
        MoveTo moveTo = new MoveTo(this.x,this.y);
        
        LineTo lineTo = new LineTo(x,y);
        
        path.setStroke(fill);
        path.getElements().addAll(moveTo,lineTo);
        
        this.panee.getChildren().add(path);
    }
    
    public void addSphere(double x, double y, double xx, Color line, Color fill){
        
        double rad =((xx-x)/2);        
        
        Sphere ss = new Sphere(rad);
        
        propri3d(ss, x+rad, y+rad, line, fill);
        this.undoHistory3D.push(ss);
        
        this.panee.getChildren().add(ss);
    }
    
    public void addCylindre(double x, double y, double xx, double yy, Color line, Color fill){
        double t = xx-x;
        if(t>50) t=50;
        Cylinder c = new Cylinder(t, yy-y);
        propri3d(c, x, y, line, fill);
        this.undoHistory3D.push(c);
        this.panee.getChildren().add(c);
    }
    
    public void addBox(double x, double y, double xx, double yy, Color line, Color fill){
        Box b = new Box(xx-x, yy-y, 70);
        propri3d(b, x, y, line, fill);
        this.undoHistory3D.push(b);
        this.panee.getChildren().add(b);
    }
    
    private void propri3d(Shape3D s, double x, double y, Color line, Color fill){
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(fill);
        material.setSpecularColor(line);
        
        Rotate rotateX = new Rotate(20, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(10, Rotate.Y_AXIS);
        
        s.getTransforms().addAll(rotateX,rotateY);
        
        s.setLayoutX(x);
        s.setLayoutY(y);
        
        s.setMaterial(material);
    }
    
    public void addPolyline(Color fill, Color line, double x, double y, double xx, double yy){
        Polyline p = new Polyline();
        p.getPoints().addAll(new Double[]{
        x, y, 
        xx, y, 
        xx+((xx-x)/4), y+((yy-y)/2),          
        xx, yy, 
        x, yy,                   
        x-((xx-x)/4), y+((yy-y)/2),
        x, y, 
        });
        p.setStroke(line);
        p.setFill(fill);
        this.panee.getChildren().add(p);
        this.undoHistory.push(p);
    }

}