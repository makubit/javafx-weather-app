package sample.Controllers;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Config.CityDataInitializer;

public class Controller {
    @FXML
    private javafx.scene.control.ScrollPane s;
    @FXML
    private javafx.scene.control.ComboBox miasto;
    @FXML
    private javafx.scene.text.TextFlow textMiasto;

    @FXML
    private javafx.scene.control.Button mode;

    private int activeButton;
    public void startNew(Stage stage) throws Exception{
        CityDataInitializer.initializeCityData();

        String fxmlFile = "/mainWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 750, 560);
        scene.getStylesheets().add("/style/style2.css");

        stage.setTitle("Weather App");
        stage.setScene(scene);
        stage.show();


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
