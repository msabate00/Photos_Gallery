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
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import photogallery_msabate.models.Img;
import photogallery_msabate.models.ImgHBOX;
import photogallery_msabate.models.Model;
import photogallery_msabate.views.CenterPane;
import photogallery_msabate.views.LeftPane;
import photogallery_msabate.views.RightPane;
import photogallery_msabate.views.View;

public class Controller {

    private Model model;
    private View view;
    private Stage stage;
    private Scene scene;
    private File favFile = new File("src\\fav.dat");

    private boolean pressedSelector = false;

    public Controller(Model m, View v, Stage s, Scene scene) {
        model = m;
        view = v;
        stage = s;
        initView();
    }

    public void initView() {
        ActualizarFav();
    }

    public void initController() {

        stage.addEventFilter(KeyEvent.ANY, keyEvent -> {
            pressedSelector = (keyEvent.isShiftDown() || keyEvent.isControlDown());
        });

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

        view.getTopPane().getAñadirImgFav().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                FavAllSelected();
            }
        });

        view.getTopPane().getMoverImg().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                try {
                    MoverAllSelected();
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);

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

            //MOSTRAR IMAGENES ******************************************************************************
            //MOSTRAR IMAGENES ******************************************************************************
            //MOSTRAR IMAGENES ******************************************************************************
            MostrarImagenesCenter();
           /* for (Img i : view.getScrollPane().getAllImg()) {

                i.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent t) {
                        Img aux;

                        if (!pressedSelector) {
                            for (Img i2 : view.getScrollPane().getAllImg()) {
                                i2.setBackground(Background.EMPTY);
                                i2.setIsSelected(Boolean.FALSE);
                            }
                            for (ImgHBOX i2 : view.getLeftPane().getAllImg()) {
                                i2.setBackground(Background.EMPTY);
                                i2.setIsSelected(Boolean.FALSE);

                            }
                            i.setBackground(new Background(new BackgroundFill(Paint.valueOf("#796272"), CornerRadii.EMPTY, Insets.EMPTY)));
                            i.setIsSelected(Boolean.TRUE);
                        } else {
                            if (i.IsSelected()) {
                                i.setBackground(Background.EMPTY);
                                i.setIsSelected(false);
                            } else {
                                i.setBackground(new Background(new BackgroundFill(Paint.valueOf("#796272"), CornerRadii.EMPTY, Insets.EMPTY)));
                                i.setIsSelected(Boolean.TRUE);
                            }
                        }

                        try {
                            aux = Img.Duplicar(i);
                            aux.getImg().setFitWidth(600);
                            aux.getImg().setPreserveRatio(true);
                            view.getRootPane().setRight(new RightPane(aux));
                        } catch (FileNotFoundException ex) {
                            // Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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

                FavButton(i);

            }*/

        }
    }

    private void MostrarImagenesCenter() {

        for (Img i : view.getScrollPane().getAllImg()) {

            i.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    Img aux;

                    if (!pressedSelector) {
                        
                        for (Img i2 : view.getScrollPane().getAllImg()) {
                            i2.setBackground(Background.EMPTY);
                            i2.setIsSelected(Boolean.FALSE);
                        }
                        for (ImgHBOX i2 : view.getLeftPane().getAllImg()) {
                            i2.setBackground(Background.EMPTY);
                            i2.setIsSelected(Boolean.FALSE);

                        }
                        i.setBackground(new Background(new BackgroundFill(Paint.valueOf("#796272"), CornerRadii.EMPTY, Insets.EMPTY)));
                        i.setIsSelected(Boolean.TRUE);
                    } else {
                        if (i.IsSelected()) {
                            
                            i.setBackground(Background.EMPTY);
                            i.setIsSelected(false);
                        } else {
                            i.setBackground(new Background(new BackgroundFill(Paint.valueOf("#796272"), CornerRadii.EMPTY, Insets.EMPTY)));
                            i.setIsSelected(true);
                         
                        }
                    }

                    try {
                        aux = Img.Duplicar(i);
                        aux.getImg().setFitWidth(600);
                        aux.getImg().setPreserveRatio(true);
                        view.getRootPane().setRight(new RightPane(aux));
                    } catch (FileNotFoundException ex) {
                        // Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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

            FavButton(i);

        }
    }

    private void FavAllSelected() {
        try {
            // FileWriter fw = new FileWriter(favFile, true);
            // PrintWriter pw = new PrintWriter(fw);

            for (Img i : view.getScrollPane().getAllImg()) {
                FileReader fr = new FileReader(favFile.getAbsoluteFile());
                BufferedReader br = new BufferedReader(fr);
                if (i.IsSelected()) {
                    String linea;
                    boolean encontrado = false;
                    while ((linea = br.readLine()) != null) {
                        if (linea.equals(i.getPath())) {
                            encontrado = true;
                            break;
                        }
                    }
                    if (encontrado) {
                        fr.close();
                        continue;
                    } else {
                        fr.close();
                        FileWriter fw = new FileWriter(favFile, true);
                        PrintWriter pw = new PrintWriter(fw);
                        pw.println(i.getPath());
                        fw.close();
                        ActualizarEstrella(i);
                    }

                } else {
                    fr.close();
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        ActualizarLeftPane();

    }

    private void MoverAllSelected() throws IOException {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(stage);
        final File path = view.getScrollPane().getPath();
        List<File> paths = new ArrayList<File>();

        //view.setCenterPane(new CenterPane());
        view.getRootPane().setRight(new RightPane());

        if (selectedDirectory != null) {
            for (Img i : view.getScrollPane().getAllImg()) {

                if (i.IsSelected()) {

                    File aux = new File(i.getPath());
                    paths.add(new File(i.getPath()));

                    String oldPath = i.getPath();
                    String newPath = selectedDirectory.getAbsolutePath() + "\\" + i.getName();
                    //Files.move(aux.toPath(), new File(newPath).toPath());
                    //Files.copy(aux.toPath(), new File(newPath).toPath());
                    System.out.println(aux.getAbsolutePath());
                    if (i.isFav()) {
                        FileReader fr = null;
                        File tempFile = new File("src\\myTempFile.dat");
                        fr = new FileReader(favFile.getAbsoluteFile());
                        BufferedReader br = new BufferedReader(fr);
                        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
                        String linea;
                        while ((linea = br.readLine()) != null) {

                            String trimmedLine = linea.trim();

                            if (linea.equals(oldPath)) {
                                bw.write(newPath + System.getProperty("line.separator"));
                                continue;
                            }
                            bw.write(linea + System.getProperty("line.separator"));
                        }
                        
                        bw.close();
                        br.close();
                        Files.move(tempFile.toPath(), favFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }

                    /* ImageView iv = new ImageView(new Image(newPath));
                     iv.setFitWidth(100);
                     iv.setFitHeight(100);
                    
                    
                    
                     Img imagen = new Img(iv, i.getName(), i.getSize(), newPath);
                     i = imagen;*/
                }

            }
            
            view.setCenterPane(new CenterPane());

            for (File f : paths) {
               // System.out.println(f);
                String newPath = selectedDirectory.getAbsolutePath() + "\\" + f.getName();
                String oldPath = f.getAbsolutePath();
                Files.copy(f.toPath(), new File(newPath).toPath());

                Files.delete(f.toPath());
            }
            
            view.setCenterPane(new CenterPane(path));
            ActualizarLeftPane();
            MostrarImagenesCenter();
            // paths.forEach((a) -> Files.move(a.toPath(), new File(selectedDirectory.getAbsolutePath() + "\\" + a.getName()).toPath()));
        } else {

        }

    }

    private void FavButton(Img i) {

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
                    } else {

                    }
                    ActualizarEstrella(i);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if (null != fr) {
                            fr.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                /* try {
                 LeftPane lp = new LeftPane();
                 view.getRootPane().setLeft(lp);
                 view.setLeftpane(lp);
                 ActualizarFav();

                 } catch (IOException ex) {
                 Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                 }*/
                ActualizarLeftPane();

            }

        });

    }

    private void ActualizarLeftPane() {
        try {
            LeftPane lp = new LeftPane();
            view.getRootPane().setLeft(lp);
            view.setLeftpane(lp);
            ActualizarFav();

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void ActualizarEstrella(Img i) throws FileNotFoundException {
        ImageView star;

        if (i.isFav()) {
            star = new ImageView(new Image("photogallery_msabate\\img\\icons\\star_yellow.png"));
        } else {
            star = new ImageView(new Image("photogallery_msabate\\img\\icons\\star_black.png"));
        }
        star.setFitHeight(30);
        star.setFitWidth(30);
        i.getFav().setGraphic(star);
    }

    private void ActualizarFav() {

        for (ImgHBOX i : view.getLeftPane().getAllImg()) {

            i.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    Img aux;

                    for (ImgHBOX i2 : view.getLeftPane().getAllImg()) {
                        i2.setBackground(Background.EMPTY);
                        i2.setIsSelected(Boolean.FALSE);
                    }
                    for (Img i2 : view.getScrollPane().getAllImg()) {
                        i2.setBackground(Background.EMPTY);
                        i2.setIsSelected(Boolean.FALSE);
                    }
                    i.setBackground(new Background(new BackgroundFill(Paint.valueOf("#796272"), CornerRadii.EMPTY, Insets.EMPTY)));
                    i.setIsSelected(Boolean.TRUE);

                    try {
                        aux = ImgHBOX.convertToImg(ImgHBOX.Duplicar(i));

                        aux.getImg().setFitWidth(600);
                        aux.getImg().setPreserveRatio(true);
                        view.getRootPane().setRight(new RightPane(aux));
                    } catch (FileNotFoundException ex) {
                        // Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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

        }

    }

}
