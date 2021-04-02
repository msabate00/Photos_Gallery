

package photogallery_msabate.controllers;


import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import photogallery_msabate.models.Model;
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
        carregarRegistre();
    }

    public void initController() {
      
        
        view.getTopPane().getSelectDir().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                mostrarExplorador();
            }
        });
        
    }

    private void carregarRegistre() {
       
    }

    private void guardarRegistre() {
//        model.setNom(view.getNomTextField().getText());
//        model.setPrimerCognom(view.getPrimerCognomTextField().getText());
//        model.setSegonCognom(view.getSegonCognomTextField().getText());
    }
    
    private void mostrarExplorador(){
        final DirectoryChooser directoryChooser =  new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(stage);
        
        if (selectedDirectory != null) {
            selectedDirectory.getAbsolutePath();
            System.out.println(selectedDirectory.getAbsolutePath());
        }
    }
}