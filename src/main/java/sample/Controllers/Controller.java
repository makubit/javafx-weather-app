package sample.Controllers;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import sample.Config.CityDataInitializer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Controller {
    @FXML
    private javafx.scene.control.ScrollPane s;
    @FXML
    private javafx.scene.control.ComboBox miasto;
    @FXML
    private javafx.scene.text.TextFlow textMiasto;

    public void generateData() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //.setItems(FXCollections.observableArrayList(city));
        //textMiasto.getChildren().addAll((Collection)city);
        //textMiasto.getChildren().add(new Text(CityDataInitializer.miasta.get(0).toString()));
        //System.out.println(CityDataInitializer.miasta.size());
        miasto.setItems(FXCollections.observableArrayList(CityDataInitializer.miasta));
    }
}
