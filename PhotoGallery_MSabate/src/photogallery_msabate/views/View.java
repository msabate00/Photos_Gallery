package photogallery_msabate.views;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;



public class View {

    private BorderPane rootPane;
    private LeftPane leftPane;
    private TopPane topPane;
    private Label nomLabel;
    private Label primerCognomLabel;
    private Label segonCognomLabel;
    private TextField nomTextField;
    private TextField primerCognomTextField;
    private TextField segonCognomTextField;
    private HBox buttonPane;
    private Button guardarButton;
    private Button carregarButton;

    public View() {
        rootPane = new BorderPane();
        rootPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
      
        leftPane = new LeftPane("aa");
        topPane = new TopPane("aa");
        
        nomLabel = new Label("Nom:");
        buttonPane = new HBox();
        buttonPane.setSpacing(5);
        guardarButton = new Button("Guardar");
        carregarButton = new Button("Carregar");

        
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
}