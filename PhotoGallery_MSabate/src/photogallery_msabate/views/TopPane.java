/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package photogallery_msabate.views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author AitramKG
 */
public class TopPane extends GridPane
{
   // private DirectoryChooser dir;
    private Button selectDir = new Button();
    private Text url = new Text("No hay ninguna ruta seleccionada");
    private Slider slider = new Slider(100, 400, 100);
    public TopPane(){
        Image img = new Image("photogallery_msabate\\img\\icons\\Selectfolder.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(30);
        view.setPreserveRatio(true);
        selectDir.setGraphic(view);
        
        slider.setShowTickMarks(true);
        
        slider.setMajorTickUnit(100f);
        slider.setBlockIncrement(50f);
        
        slider.setPadding(new Insets(0,0,0,50));
        
        setConstraints(view, 0, 0);
        setConstraints(url, 1, 0);
        setConstraints(slider, 2, 0);
        getChildren().addAll(selectDir, url, slider);
        
        
        
        
    }

   
            
    public Button getSelectDir() {
        return selectDir;
    }
    
    public void actualizarRuta(String r){
        getChildren().remove(url);
        url.setText(r);
        getChildren().add(url);
    }
    
    public Slider getSlider(){
        return slider;
    }
    
    
}
