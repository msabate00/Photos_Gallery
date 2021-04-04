/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package photogallery_msabate.views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author AitramKG
 */
public class LeftPane extends StackPane{
   // private DirectoryChooser dir;
    Button selectDir = new Button("Seleccionar Directorio");
    public LeftPane(){
        getChildren().add(selectDir);
    }
    
     public Button getSelectDir() {
        return selectDir;
    }
}
