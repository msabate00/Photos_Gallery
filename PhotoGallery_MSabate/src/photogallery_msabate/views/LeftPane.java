/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package photogallery_msabate.views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author AitramKG
 */
public class LeftPane extends GridPane{
   // private DirectoryChooser dir;
    Text title = new Text("Favoritos");
    Button selectDir = new Button("Seleccionar Directorio");
    public LeftPane(){
        
        
        setPadding(new Insets(30, 10, 10, 20));
        setVgap(10);
        getChildren().add(title);
    }
    
     public Button getSelectDir() {
        return selectDir;
    }
}
