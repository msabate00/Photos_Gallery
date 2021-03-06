/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photogallery_msabate.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author AitramKG
 */
public class ImgHBOX extends HBox {

    private Boolean isSelected = false;
    private ImageView img;
    private String name;
    private int size;
    private Button main, fav;
    private String path;

    public ImgHBOX(ImageView view, String n, int s, String p) {
        img = view;
        name = n;
        size = s;
        path = p;

       // Text tname = new Text(this.name); 
        // Text tsize = new Text(String.valueOf(this.size) + " Kb");
       // Button b = new Button();
        //b.setGraphic(view);
        //b.setText(name + "\n" + size + "Kb");
        //b.textAlignmentProperty().set(TextAlignment.CENTER);
        //b.setContentDisplay(ContentDisplay.TOP);
        //b.setWrapText(true);
//        Button bf = new Button();
//        
//        ImageView star = new ImageView(new Image("photogallery_msabate\\img\\icons\\star_black.png"));
//        star.setFitHeight(30);
//        star.setFitWidth(30);
//        bf.setGraphic(star);
//        bf.setMinSize(30, 30);
//        bf.setMaxSize(30, 30);
        //main = b;
//        fav = bf;
        Text t = new Text(getName());
        t.setWrappingWidth(100);
        t.setTextAlignment(TextAlignment.CENTER);

        //b.setContentDisplay(ContentDisplay.);
        //getChildren().addAll(b, fav);  
        setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(view, t);

    }

    public String getPath() {
        return this.path;
    }

    public static ImgHBOX Duplicar(ImgHBOX i) throws FileNotFoundException, IOException {
        //new ImageView(new Image(i.img))
        
        FileInputStream fs = new FileInputStream(i.getPath());
        
        ImageView viewaux = new ImageView(new Image(fs));

        ImgHBOX aux = new ImgHBOX(viewaux, i.getName(), i.getSize(), i.getPath());
        
        fs.close();
        return aux;

    }
    public static Img convertToImg(ImgHBOX i) throws FileNotFoundException, IOException{
        FileInputStream fs = new FileInputStream(i.getPath());
        
        ImageView viewaux = new ImageView(new Image(fs));
        Img aux = new Img(viewaux, i.getName(), i.getSize(), i.getPath());
        
        fs.close();
    return aux;
    }

    /**
     * @return the isSelected
     */
    public Boolean IsSelected() {
        return isSelected;
    }

    /**
     * @return the img
     */
    public ImageView getImg() {
        return img;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @return the main
     */
    

    /**
     * @return the fav
     */
    public Button getFav() {
        return fav;
    }

    /**
     * @param isSelected the isSelected to set
     */
    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

}
