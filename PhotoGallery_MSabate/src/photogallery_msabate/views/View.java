package photogallery_msabate.views;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class View {

    private GridPane rootPane;
    private Label nomLabel;
    private Label primerCognomLabel;
    private Label segonCognomLabel;
    private TextField nomTextField;
    private TextField primerCognomTextField;
    private TextField segonCognomTextField;
    private HBox buttonPane;
    private Button guardarButton;
    private Button carregarButton;

    public View() {
        rootPane = new GridPane();
        rootPane.setAlignment(Pos.CENTER);
        rootPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        rootPane.setHgap(5.5);
        rootPane.setVgap(5.5);

        nomLabel = new Label("Nom:");
        primerCognomLabel = new Label("Primer cognom:");
        segonCognomLabel = new Label("Segon cognom:");
        nomTextField = new TextField();
        primerCognomTextField = new TextField();
        segonCognomTextField = new TextField();
        buttonPane = new HBox();
        buttonPane.setSpacing(5);
        guardarButton = new Button("Guardar");
        carregarButton = new Button("Carregar");

        rootPane.add(nomLabel, 0, 0);
        rootPane.add(primerCognomLabel, 0, 1);
        rootPane.add(segonCognomLabel, 0, 2);
        rootPane.add(nomTextField, 1, 0);
        rootPane.add(primerCognomTextField, 1, 1);
        rootPane.add(segonCognomTextField, 1, 2);

        buttonPane.getChildren().addAll(guardarButton, carregarButton);
        rootPane.add(buttonPane, 1, 3);
        buttonPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(buttonPane, HPos.RIGHT);
    }

    public GridPane getRootPane() {
        return rootPane;
    }

    public Label getNomLabel() {
        return nomLabel;
    }

    public Label getPrimerCognomLabel() {
        return primerCognomLabel;
    }

    public Label getSegonCognomLabel() {
        return segonCognomLabel;
    }

    public TextField getNomTextField() {
        return nomTextField;
    }

    public TextField getPrimerCognomTextField() {
        return primerCognomTextField;
    }

    public TextField getSegonCognomTextField() {
        return segonCognomTextField;
    }

    public HBox getButtonPane() {
        return buttonPane;
    }

    public Button getGuardarButton() {
        return guardarButton;
    }

    public Button getCarregarButton() {
        return carregarButton;
    }
}