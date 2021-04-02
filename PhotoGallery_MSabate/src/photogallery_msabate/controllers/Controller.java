

package photogallery_msabate.controllers;


import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import photogallery_msabate.models.Model;
import photogallery_msabate.views.CenterPane;
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
        
    }
    
    private void mostrarExplorador() throws IOException{
        final DirectoryChooser directoryChooser =  new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(stage);
        
        if (selectedDirectory != null) {
            selectedDirectory.getAbsolutePath();
            view.getRootPane().setCenter(new CenterPane(selectedDirectory));
            view.getTopPane().actualizarRuta(selectedDirectory.getPath());
            
        }
    }
}