import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import photogallery_msabate.controllers.Controller;
import photogallery_msabate.models.Model;
import photogallery_msabate.views.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
      
        Model m = new Model("Armando", "Bronca", "Segura");
        View v = new View();
        Scene scene = new Scene(v.getRootPane(), 300, 200);
        Controller c = new Controller(m, v, primaryStage, scene);
        c.initController();

        primaryStage.setTitle("Photo Viewer By Marti Sabaté");
        //Scene scene = new Scene(v.getRootPane(), 300, 200);
        
        scene.getStylesheets().add("photogallery_msabate/views/styles/centerPane.css");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}