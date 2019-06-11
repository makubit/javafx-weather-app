package sample.Controllers;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import sample.Config.CityDataInitializer;

public class Controller {
    @FXML
    private javafx.scene.control.ScrollPane s;
    @FXML
    private javafx.scene.control.ComboBox miasto;
    @FXML
    private javafx.scene.text.TextFlow textMiasto;

    public void generateData() {
        miasto.setItems(FXCollections.observableArrayList(CityDataInitializer.miasta));
    }
}
