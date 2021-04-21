/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package photogallery_msabate.views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    
    private Button moverImg = new Button("Mover Imagenes A Otro Directorio");
    private Button añadirImgFav = new Button("Añadir Seleccionados a Favoritos");
    
    private CheckBox checkbox = new CheckBox("Mostrar Solo Favoritos");
    
    private Text url = new Text("No hay ninguna ruta seleccionada");
    private String urlr;
   // private Text sliderTxt = new Text("Cambar Tamaño Miniaturas: ");
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
        
        checkbox.setSelected(false);
        //checkbox.setPadding(new Insets(0,0,0,0));
        
        
        
        setConstraints(view, 0, 0);
        setConstraints(url, 1, 0);
       
        setConstraints(slider, 3, 0);
        setConstraints(moverImg, 4, 0);
        setConstraints(añadirImgFav, 5, 0);
        setConstraints(checkbox,6,0);
        
       
        
        getChildren().addAll(selectDir, url, slider, moverImg, añadirImgFav, checkbox);
        
        
        
        
    }

   
            
    public Button getSelectDir() {
        return selectDir;
    }
    
    public void actualizarRuta(String r){
        getChildren().remove(getUrl());
        getUrl().setText(r);
        getChildren().add(getUrl());
    }
    
    public Slider getSlider(){
        return slider;
    }

    /**
     * @return the moverImg
     */
    public Button getMoverImg() {
        return moverImg;
    }

    /**
     * @return the añadirImgFav
     */
    public Button getAñadirImgFav() {
        return añadirImgFav;
    }

    /**
     * @return the checkbox
     */
    public CheckBox getCheckbox() {
        return checkbox;
    }

    /**
     * @return the url
     */
    public Text getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(Text url) {
        this.url = url;
    }
    
    
}
