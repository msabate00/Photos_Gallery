/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package photogallery_msabate.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author AitramKG
 */
public class CenterPane extends FlowPane{
    private File[] files;
    public CenterPane(File dir) throws IOException{
       
        files = dir.listFiles();
        if(files != null){
            for(File f : files){
                
                if(f.getName().substring(f.getName().lastIndexOf(".") + 1, f.getName().length()).equals("jpg")){
                  Image image = new Image(new FileInputStream(f));
                  ImageView view = new ImageView(image);
                  view.setFitWidth(100);
                  view.setFitHeight(100);
                  
                  //view.getStyleClass().add("imagenes");
                  view.setStyle("-fx-padding: 50");
                  //view.maxHeight(300);
                  //view.setPreserveRatio(true);
                  getChildren().add(view);              
                }
            }
        }
    
    }
}
