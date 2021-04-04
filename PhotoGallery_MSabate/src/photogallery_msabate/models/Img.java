/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package photogallery_msabate.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
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
   private String path;
   
   public Img(ImageView view, String n, int s, String p){
        img = view;
        name = n;
        size = s;
        path = p;
        
       // Text tname = new Text(this.name); 
       // Text tsize = new Text(String.valueOf(this.size) + " Kb");
        
        this.setAlignment(Pos.CENTER);
        
        Button b = new Button();
        b.setGraphic(view);
        b.setText(name + "\n" + size + "Kb");
        b.textAlignmentProperty().set(TextAlignment.CENTER);
        b.setContentDisplay(ContentDisplay.TOP);
        but = b;
       
        //b.setContentDisplay(ContentDisplay.);
        
        getChildren().addAll(b);    
    }
   public String getPath(){
       return this.path;
   }
   
  public static Img Duplicar(Img i) throws FileNotFoundException{
      //new ImageView(new Image(i.img))
      ImageView viewaux = new ImageView(new Image(new FileInputStream(i.getPath())));
      
      Img aux = new Img(viewaux, i.name, i.size, i.getPath());
      
      return aux;
      
  }
   
   
}
