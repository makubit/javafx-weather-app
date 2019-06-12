package sample.Controllers;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Config.CityDataInitializer;

import static sample.Main.pogoda;

public class Controller {
    @FXML
    private javafx.scene.control.ScrollPane s;
    @FXML
    private javafx.scene.control.ComboBox miasto;
    @FXML
    private javafx.scene.text.TextFlow textMiasto;
    @FXML
    private javafx.scene.control.Label label1;
    @FXML
    private javafx.scene.control.Label label2;
    @FXML
    private javafx.scene.control.Label label3;
    @FXML
    private javafx.scene.control.Label label4;
    @FXML
    private javafx.scene.control.Label label5;
    @FXML
    private javafx.scene.control.Label label6;
    @FXML
    private javafx.scene.control.Label label7;
    @FXML
    private javafx.scene.control.Label label8;
    @FXML
    private javafx.scene.control.Label label9;
    @FXML
    private javafx.scene.control.Button mode;


    private int activeButton;
    int zm = 0;

    public void startNew(Stage stage) throws Exception{
        CityDataInitializer.initializeCityData();

        String fxmlFile = "/mainWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        if( zm == 1 )
            set();

        Scene scene = new Scene(rootNode,  700, 580);
        scene.getStylesheets().add("/style/style2.css");

        stage.setTitle("Weather App");
        stage.setScene(scene);
        stage.show();


    }
    public void set()
    {
        this.label1.setText(pogoda[0]);
        System.out.println(pogoda[0]);
        this.label2.setText(pogoda[1]);
        System.out.println(pogoda[1]);
        this.label3.setText(pogoda[2]);
        this.label4.setText(pogoda[3]);
        this.label5.setText(pogoda[4]);
        this.label6.setText(pogoda[5]);
        this.label7.setText(pogoda[6]);
        this.label8.setText(pogoda[7]);
        this.label9.setText(pogoda[8]);
    }
    public void setOnClick(ActionEvent actionEvent) throws Exception
    {
        set();
        zm = 1;
    }

    public void modeOnClick(ActionEvent actionEvent) throws Exception
    {
        System.out.println("1");
        Stage stage = (Stage) mode.getScene().getWindow();
        startNew(stage);


    }


    public void generateData() {
        miasto.setItems(FXCollections.observableArrayList(CityDataInitializer.miasta));
    }
}
