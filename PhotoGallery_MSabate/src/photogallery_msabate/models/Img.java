/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photogallery_msabate.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
public class Img extends VBox {

    private Boolean isSelected = false;
    private ImageView img;
    private String name;
    private int size;
    private Button main, fav;
    private String path;

    public Img(ImageView view, String n, int s, String p) throws FileNotFoundException {
        img = view;
        name = n;
        size = s;
        path = p;

        Button bf = new Button();
        ImageView star;

        if (this.isFav()) {
            star = new ImageView(new Image("photogallery_msabate\\img\\icons\\star_yellow.png"));
        } else {
            star = new ImageView(new Image("photogallery_msabate\\img\\icons\\star_black.png"));
        }

        star.setFitHeight(30);
        star.setFitWidth(30);

        bf.setGraphic(star);

        bf.setMinSize(30, 30);
        bf.setMaxSize(30, 30);
        //main = b;
        fav = bf;
        Text t = new Text(getName() + "\n" + getSize() + "Kb");
        t.setWrappingWidth(100);
        t.setTextAlignment(TextAlignment.CENTER);

        //b.setContentDisplay(ContentDisplay.);
        //getChildren().addAll(b, fav);  
        setAlignment(Pos.CENTER);
        getChildren().addAll(bf, view, t);

    }

    public String getPath() {
        return this.path;
    }

    public static Img Duplicar(Img i) throws FileNotFoundException, IOException {
        //new ImageView(new Image(i.img))
        FileInputStream fs = new FileInputStream(i.getPath());
        ImageView viewaux = new ImageView(new Image(fs));

        Img aux = new Img(viewaux, i.getName(), i.getSize(), i.getPath());
        fs.close();
        return aux;

    }

    public Boolean isFav() throws FileNotFoundException {

        FileReader fr = null;
        File favFile = new File("src\\fav.dat");

        try {
            fr = new FileReader(favFile.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {

                String trimmedLine = linea.trim();

                if (linea.equals(this.getPath())) {
                    br.close();
                    return true;
                }
            }
            br.close();
            return false;

        } catch (IOException e) {
            e.printStackTrace();

        }

        return false;
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
