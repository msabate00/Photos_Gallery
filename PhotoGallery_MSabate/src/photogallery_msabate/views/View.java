package photogallery_msabate.views;

import java.io.File;
import java.io.IOException;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;



public class View {

    private BorderPane rootPane;
    private LeftPane leftPane;
    private RightPane rightPane;
    private TopPane topPane;
    private CenterPane centerPane;
    
    final ScrollBar sc = new ScrollBar();


    public View() throws IOException {
        rootPane = new BorderPane();
        rootPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
      
        leftPane = new LeftPane();
        rightPane = new RightPane();
        topPane = new TopPane();
        centerPane = new CenterPane(new File("nulo"));
        
       

        
        rootPane.setLeft(leftPane);
        rootPane.setTop(topPane);
    }

    public BorderPane getRootPane() {
        return rootPane;
    }
    
    public LeftPane getLeftPane(){
        return leftPane;
    }
    public TopPane getTopPane(){
        return topPane;
    }
    
    
    public CenterPane getScrollPane(){
        return centerPane;
    }
    public FlowPane getCenterPane(){
        return centerPane.getFlowPane();
    }
    public void setCenterPane(CenterPane cp){
        centerPane = cp;
        rootPane.setCenter(centerPane);
    }
    
    public ScrollBar getSc(){
        return sc;
    }
    
}