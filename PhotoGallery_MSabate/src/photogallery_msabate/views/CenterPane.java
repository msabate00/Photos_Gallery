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
import javafx.geometry.Orientation;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import photogallery_msabate.models.Img;

/**
 *
 * @author AitramKG
 */
public class CenterPane extends ScrollPane {

    private final File[] files;
    private List<Img> img = new ArrayList<Img>();
    private FlowPane fp = new FlowPane();
    private File path;

    public CenterPane() {
        files = null;
    }

    public CenterPane(File dir) throws IOException {

        setHbarPolicy(ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollBarPolicy.ALWAYS);
        path = dir.getAbsoluteFile();
        files = dir.listFiles();
        if (files != null) {

            for (File f : files) {

                if (f.getName().substring(f.getName().lastIndexOf(".") + 1, f.getName().length()).equals("jpg")) {

                    FileInputStream fs = new FileInputStream(f);

                    Image image = new Image(fs);
                    ImageView view = new ImageView(image);
                    view.setFitWidth(100);
                    view.setFitHeight(100);

                    Img imagen = new Img(view, f.getName(), ((int) f.length() / 1000), f.getAbsolutePath());
                    fs.close();
                    img.add(imagen);
                    fp.getChildren().add(imagen);

                }
            }

            fp.setVgap(20);
            fp.setHgap(20);
            fp.setPrefWrapLength(300);

            setFitToHeight(true);
            setFitToWidth(true);
            setContent(fp);

        }

    }

    public CenterPane(File dir, File favFile, boolean b) throws IOException {

        setHbarPolicy(ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollBarPolicy.ALWAYS);
        path = dir.getAbsoluteFile();
        files = dir.listFiles();
        if (files != null) {

            for (File f : files) {

                if (f.getName().substring(f.getName().lastIndexOf(".") + 1, f.getName().length()).equals("jpg")) {

                    FileInputStream fs = new FileInputStream(f);

                    Image image = new Image(fs);
                    ImageView view = new ImageView(image);
                    view.setFitWidth(100);
                    view.setFitHeight(100);

                    Img imagen = new Img(view, f.getName(), ((int) f.length() / 1000), f.getAbsolutePath());
                    fs.close();
                    img.add(imagen);
                   // fp.getChildren().add(imagen);

                }
            }

            List<Img> imgs = new ArrayList<Img>();

            if (b == true) {
                for (Img i : this.img) {

                    FileReader fr = new FileReader(favFile.getAbsoluteFile());
                    BufferedReader br = new BufferedReader(fr);
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        if (linea.equals(i.getPath())) {
                            imgs.add(i);
                            break;
                        }

                    }
                    br.close();

                }
            } else {
                imgs = this.img;
            }

            
            for(Img i : imgs){
            
                 fp.getChildren().add(i);
            }
            
            
            fp.setVgap(20);
            fp.setHgap(20);
            fp.setPrefWrapLength(300);

            setFitToHeight(true);
            setFitToWidth(true);
            setContent(fp);

        }

    }

    public FlowPane getFlowPane() {
        return fp;
    }

    public List<Img> getAllImg() {
        return img;
    }


    /*public void actualizar(File dir) throws FileNotFoundException {
     files = dir.listFiles();
     if (files != null) {
     for (File f : files) {

     if (f.getName().substring(f.getName().lastIndexOf(".") + 1, f.getName().length()).equals("jpg")) {
     Image image = new Image(new FileInputStream(f));
     ImageView view = new ImageView(image);
     view.setFitWidth(100);
     view.setFitHeight(100);

     //view.getStyleClass().add("imagenes");
     view.setStyle("-fx-margin: 50");
     //view.maxHeight(300);
     //view.setPreserveRatio(true);
     getChildren().addAll(view);

     }
     }
     }
     }*/
    /**
     * @return the path
     */
    public File getPath() {
        return path;
    }

}
