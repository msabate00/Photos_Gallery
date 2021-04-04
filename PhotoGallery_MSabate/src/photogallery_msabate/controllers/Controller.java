package photogallery_msabate.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import photogallery_msabate.models.Img;
import photogallery_msabate.models.Model;
import photogallery_msabate.views.CenterPane;
import photogallery_msabate.views.RightPane;
import photogallery_msabate.views.View;

public class Controller {

    private Model model;
    private View view;
    private Stage stage;

    public Controller(Model m, View v, Stage s) {
        model = m;
        view = v;
        stage = s;
        initView();
    }

    public void initView() {

    }

    public void initController() {

        view.getTopPane().getSelectDir().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                try {
                    mostrarExplorador();
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        view.getTopPane().getSlider().valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                try {
                    List<Img> img = view.getScrollPane().getAllImg();
                    for (Img view : img) {
                        view.img.setFitHeight((double) t);
                        view.img.setFitWidth((double) t);
                    }
                } catch (Exception e) {

                }
            }
        });

       for(Img i : view.getScrollPane().getAllImg()){
           
           
           i.but.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
           
               @Override
               public void handle(MouseEvent t) {
                   view.getRootPane().setRight(new RightPane(i));
                   System.out.print(i.name);
               }
           });
          
       } 
        
       // view.getScrollPane().getAllImg()
        
        
    }

    private void mostrarExplorador() throws IOException {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            selectedDirectory.getAbsolutePath();
            CenterPane cp = new CenterPane(selectedDirectory);
            view.setCenterPane(cp);
            //view.getCenterPane().actualizar(selectedDirectory);
            view.getTopPane().actualizarRuta(selectedDirectory.getPath());

        }
    }
}
