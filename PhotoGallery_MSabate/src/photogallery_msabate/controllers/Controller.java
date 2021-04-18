package photogallery_msabate.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import photogallery_msabate.models.Img;
import photogallery_msabate.models.Model;
import photogallery_msabate.views.CenterPane;
import photogallery_msabate.views.LeftPane;
import photogallery_msabate.views.RightPane;
import photogallery_msabate.views.View;

public class Controller {

    private Model model;
    private View view;
    private Stage stage;
    private File favFile = new File("src\\fav.dat");

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
                        view.getImg().setFitHeight((double) t);
                        view.getImg().setFitWidth((double) t);
                    }
                } catch (Exception e) {

                }
            }
        });

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
            //UpdateController();

            for (Img i : view.getScrollPane().getAllImg()) {

                i.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent t) {
                        Img aux;

                        for (Img i2 : view.getScrollPane().getAllImg()) {
                            i2.setBackground(Background.EMPTY);
                            i2.setIsSelected(Boolean.FALSE);
                        }
                        i.setBackground(new Background(new BackgroundFill(Paint.valueOf("#796272"), CornerRadii.EMPTY, Insets.EMPTY)));
                        i.setIsSelected(Boolean.TRUE);

                        try {
                            aux = Img.Duplicar(i);
                            aux.getImg().setFitWidth(600);
                            aux.getImg().setPreserveRatio(true);
                            view.getRootPane().setRight(new RightPane(aux));
                        } catch (FileNotFoundException ex) {
                            // Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                });

                i.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent t) {

                        if (!i.IsSelected()) {
                            i.setBackground(new Background(new BackgroundFill(Paint.valueOf("#4f7e8e"), CornerRadii.EMPTY, Insets.EMPTY)));
                        }
                    }

                });
                i.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent t) {

                        //if(i.backgroundProperty().getValue());
                        if (!i.IsSelected()) {
                            i.setBackground(Background.EMPTY);
                        }

                    }

                });

                i.getFav().setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        Boolean encontrado = false;
                        FileReader fr = null;
                        File tempFile = new File("src\\myTempFile.dat");
                        
                        
                        try {
                            fr = new FileReader(favFile.getAbsoluteFile());
                            BufferedReader br = new BufferedReader(fr);
                            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
                            String linea;
                            while ((linea = br.readLine()) != null) {
                                
                                String trimmedLine = linea.trim();
                                
                                
                                if (linea.equals(i.getPath())) {
                                    encontrado = true;
                                    System.out.println(i.getPath());
                                    continue;
                                }
                                bw.write(linea + System.getProperty("line.separator"));
                            }
                            
                            bw.close();
                            br.close();
                            Files.move(tempFile.toPath(), favFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            //tempFile.delete();
                            
                            if (!encontrado) {
                                try {
                                    FileWriter fw = new FileWriter(favFile, true);
                                    PrintWriter pw = new PrintWriter(fw);
                                    pw.println(i.getPath());
                                    
                                    fw.close();
                                    
                                } catch (IOException ex) {
                                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }finally{
                            
                            try{
                               if(null != fr){
                                   fr.close();
                               }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                           
                        
                        }

                    }

                });

            }

        }
    }
    
   
}
