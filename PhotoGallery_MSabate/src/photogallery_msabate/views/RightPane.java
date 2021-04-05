/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package photogallery_msabate.views;

import java.awt.Color;
import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import photogallery_msabate.models.Img;

/**
 *
 * @author AitramKG
 */
public class RightPane extends VBox{
    
    private Text titulo;
    
    
    public RightPane(){}
    
    public RightPane(Img i){
        
        if(!getChildren().isEmpty()){
            getChildren().removeAll();
        }
        
        titulo = new Text("Nombre: " + i.name + "\n Tamaño: " + String.valueOf(i.size) + " KB");
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setFill(Paint.valueOf("white"));
        titulo.setFont(Font.font("Verdana", FontWeight.LIGHT, 15));
        
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"), CornerRadii.EMPTY, Insets.EMPTY)));
        //i.img.setY(getLayoutY()/2);
        
        getChildren().addAll(titulo,i.img);
        
    }
    
    
    
}
