/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photogallery_msabate.views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javax.swing.JScrollPane;
import photogallery_msabate.models.Img;
import photogallery_msabate.models.ImgHBOX;

/**
 *
 * @author AitramKG
 */
public class LeftPane extends VBox {

    // private DirectoryChooser dir;
    Text title = new Text("Favoritos");
    ScrollPane sp = new ScrollPane();
    VBox vp = new VBox();
    File favFile = new File("src\\fav.dat");
    private List<ImgHBOX> img = new ArrayList<ImgHBOX>();

    public LeftPane() throws FileNotFoundException, IOException {
        setPadding(new Insets(30, 10, 10, 20));
        getChildren().addAll(title, sp);
        setVgrow(sp, Priority.ALWAYS);
        vp.setPadding(new Insets(10,10,10,10));
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        

        FileReader fr = new FileReader(favFile);
        BufferedReader br = new BufferedReader(fr);

        String path;
        while ((path = br.readLine()) != null) {
            
            File f = new File(path);
            FileInputStream fs = new FileInputStream(f);
            
            Image image = new Image(fs);
            ImageView view = new ImageView(image);
            view.setFitWidth(50);
            view.setFitHeight(50);
            ImgHBOX imagen = new ImgHBOX(view, f.getName(), ((int) f.length()/1000), f.getAbsolutePath());
            /*
            hp.setAlignment(Pos.CENTER_LEFT);
            hp.getChildren().addAll(imagen.getImg(), new Text(imagen.getName()));*/
            fs.close();
            img.add(imagen);
            vp.getChildren().add(imagen);
        }
        br.close();
        sp.setContent(vp);

    }

    /**
     * @return the img
     */
    public List<ImgHBOX> getAllImg() {
        return img;
    }

}
