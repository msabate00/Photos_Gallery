import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import photogallery_msabate.controllers.Controller;
import photogallery_msabate.models.Model;
import photogallery_msabate.views.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Model m = new Model("Armando", "Bronca", "Segura");
        View v = new View();
        Controller c = new Controller(m, v);
        c.initController();

        primaryStage.setTitle("MVC");
        primaryStage.setScene(new Scene(v.getRootPane(), 300, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}