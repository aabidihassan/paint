import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Aabidi & Ezzahri
 */

public class Paint extends Application {
    private ThePane paneCen;
    private TheShapes ss;
    private TopMenu2 mm2;
    
    
    @Override
    public void start(Stage primaryStage) {
        
        this.ss = new TheShapes();
        this.mm2 = new TopMenu2();
        

        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 1300, 700);
        PerspectiveCamera camera = new PerspectiveCamera();
        scene.setCamera(camera);

        pane.setTop(mm2.btns3);
        pane.setLeft(ss.btns1);
        
        this.paneCen = new ThePane();
        
        mm2.neew.setOnAction(e->{
            if(!paneCen.undoHistory.empty() || !paneCen.undoHistory3D.empty()) saveFile(paneCen.panee);
            this.paneCen = new ThePane();
            pane.setCenter(paneCen.panee);
            actions(paneCen.panee, scene, pane);
                });

        pane.setCenter(paneCen.panee);
        actions(paneCen.panee, scene, pane);
        
        
        
     
        primaryStage.setTitle("Paint");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("imgs/icon.png"));
        primaryStage.show();
    }
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void saveFile(Pane panee){
        FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save");
            
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG(*.png)", "*.png"));
            File file = fileChooser.showSaveDialog(null);

            if(file != null){
                try {
                    WritableImage writableImage = new WritableImage((int) panee.getWidth(),
                            (int)panee.getHeight());
                    panee.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException ex) { ex.printStackTrace(); }
            }
    }
    
    public void actions(Pane p, Scene scene, BorderPane pane){
        p.setOnMousePressed((e)->{
            paneCen.x = e.getX(); paneCen.y = e.getY();
            p.setOnMouseReleased((eh)->{
                    if(ss.carbtn1.isSelected()){
                        ss.tools1.getSelectedToggle().setSelected(false);
                        ss.carbtn1.setSelected(true);
                        paneCen.addRectangle(mm2.cpFill.getValue(), mm2.cpLine.getValue() , e.getX(), e.getY(), eh.getX());
                }
                    else if(ss.linebtn1.isSelected()){
                        ss.tools1.getSelectedToggle().setSelected(false);
                        ss.linebtn1.setSelected(true);
                        paneCen.addCircle(mm2.cpFill.getValue(), mm2.cpLine.getValue() , e.getX(), e.getY(), eh.getX(), eh.getY());
                }
                    else if(ss.circlebtn1.isSelected()){
                        ss.tools1.getSelectedToggle().setSelected(false);
                        ss.circlebtn1.setSelected(true);
                        paneCen.addBigRectangle(mm2.cpFill.getValue(), mm2.cpLine.getValue() , e.getX(), e.getY(), eh.getX(), eh.getY());
                }
                    else if(ss.elip.isSelected()){
                        ss.tools1.getSelectedToggle().setSelected(false);
                        ss.elip.setSelected(true);
                        paneCen.addEllipse(mm2.cpFill.getValue(), mm2.cpLine.getValue() , e.getX(), e.getY(), eh.getX(), eh.getY());
                }
                    else if(ss.rect.isSelected()){
                        ss.tools1.getSelectedToggle().setSelected(false);
                        ss.rect.setSelected(true);
                        paneCen.addLine(mm2.cpLine.getValue() , e.getX(), e.getY(), eh.getX(), eh.getY());
                }
                    else if(ss.tri.isSelected()){
                        ss.tools1.getSelectedToggle().setSelected(false);
                        ss.tri.setSelected(true);
                        paneCen.addTriangle(mm2.cpFill.getValue(), mm2.cpLine.getValue() , e.getX(), e.getY(), eh.getX(), eh.getY());
                }
                    else if(ss.polyline.isSelected()){
                        ss.tools1.getSelectedToggle().setSelected(false);
                        ss.polyline.setSelected(true);
                        paneCen.addPolyline(mm2.cpFill.getValue(), mm2.cpLine.getValue() , e.getX(), e.getY(), eh.getX(), eh.getY());
                    }
                    else if(ss.sphe.isSelected()){
                        ss.tools1.getSelectedToggle().setSelected(false);
                        ss.sphe.setSelected(true);
                        paneCen.addSphere(e.getX(), e.getY(), eh.getX(), mm2.cpFill.getValue(), mm2.cpLine.getValue());
                    }
                    else if(ss.cylin.isSelected()){
                        ss.tools1.getSelectedToggle().setSelected(false);
                        ss.cylin.setSelected(true);
                        paneCen.addCylindre(e.getX(), e.getY(), eh.getX(), eh.getY(), mm2.cpFill.getValue(), mm2.cpLine.getValue());
                    }
                    else if(ss.box.isSelected()){
                        ss.tools1.getSelectedToggle().setSelected(false);
                        ss.box.setSelected(true);
                        paneCen.addBox(e.getX(), e.getY(), eh.getX(), eh.getY(), mm2.cpFill.getValue(), mm2.cpLine.getValue());
                    }
            });
        });
        
        mm2.carbtn.setOnAction(e->paneCen.undo(true));
        
        mm2.linebtn.setOnAction(e->paneCen.redo(true));
        
        mm2.close.setOnAction(e->{
            if(!paneCen.undoHistory.empty() || !paneCen.undoHistory3D.empty()) saveFile(p);
                Platform.exit();
            
        });
        
        mm2.threeD.setOnAction((eh)->{
            mm2.carbtn.setOnAction(e->paneCen.undo(false));
            mm2.linebtn.setOnAction(e->paneCen.redo(false));
            pane.setLeft(ss.btns2);
            mm2.cpLine.setValue(Color.RED);
        });
        mm2.twoD.setOnAction((eh)->{
            mm2.carbtn.setOnAction(e->paneCen.undo(true));
            mm2.linebtn.setOnAction(e->paneCen.redo(true));
            pane.setLeft(ss.btns1);
            mm2.cpLine.setValue(Color.BLACK);
        });
        
        mm2.rectbtn.setOnAction((e)->{
            saveFile(p);
        });
        
        
        
        p.setOnMouseDragged((eh)->{
                if(mm2.gomme.isSelected()){
                    mm2.pen.setSelected(false);
                    paneCen.pen(eh.getX(), eh.getY(), mm2.slider.getValue()*2, Color.WHITE);
                }
                else if(mm2.pen.isSelected()){
                    mm2.gomme.setSelected(false);
                    paneCen.pen(eh.getX(), eh.getY(), mm2.slider.getValue(), mm2.cpLine.getValue());
                }
                else if(ss.threD.isSelected()){
                    ss.tools1.getSelectedToggle().setSelected(false);
                    ss.threD.setSelected(true);
                    paneCen.draw3d(eh.getX(), eh.getY(), mm2.cpLine.getValue());
                }
            });
    }
}
