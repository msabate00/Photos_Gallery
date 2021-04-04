/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package photogallery_msabate.models;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author AitramKG
 */
public class Img extends VBox{
   public ImageView img;
   public String name;
   public int size;
   public Button but;
   
   public Img(ImageView view, String n, int s){
        img = view;
        name = n;
        size = s;
        
       // Text tname = new Text(this.name); 
       // Text tsize = new Text(String.valueOf(this.size) + " Kb");
        
        this.setAlignment(Pos.CENTER);
        
        Button b = new Button();
        b.setGraphic(view);
        b.setText(name + "\n" + size);
        b.textAlignmentProperty().set(TextAlignment.CENTER);
        b.setContentDisplay(ContentDisplay.TOP);
        
       
        //b.setContentDisplay(ContentDisplay.);
        
        getChildren().addAll(b);
        
        
   }
   
   
}
