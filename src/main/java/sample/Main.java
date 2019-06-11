package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;
import sample.Config.CityDataInitializer;
import sample.Config.OWMApiConfig;
import sample.Entities.TestEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class Main extends Application {


    @FXML private javafx.scene.control.Button mode;

    private int activeButton;

    public void modeOnClick(ActionEvent actionEvent)
    {
        this.activeButton = 1;
    }

    @Override
    public void start(Stage stage) throws Exception{
        CityDataInitializer.initializeCityData();

        String fxmlFile = "/mainWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 750, 560);


        scene.getStylesheets().add("/style/style.css");

        switch(activeButton) {
            case 0:
                scene.getStylesheets().add("/style/style.css");
                break;
            case 1:
                scene.getStylesheets().add("/style/style2.css");
                break;
        }

        stage.setTitle("Weather App");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {

        /* PRZYKŁAD POŁĄCZENIA Z BAZĄ DANYCH */
/*
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TestEntity model = new TestEntity();
        model.setDate("12.12.1990");
        model.setWeekDay("monday");

        entityManager.getTransaction().begin();
        entityManager.persist(model);
        entityManager.getTransaction().commit();

        System.out.println(entityManager.contains(model));
        entityManager.close();
        entityManagerFactory.close();
*/
        /* * * * * * * * * * * * */
        /* TEST POŁĄCZENIA Z API */

        OWM owm = new OWM((OWMApiConfig.getInstance().getApiKey()));
        owm.setUnit(OWM.Unit.METRIC);
        System.out.println((OWMApiConfig.getInstance().getApiKey()));

        try {
            //szukamy miasta -> Radom
            CurrentWeather cwd = owm.currentWeatherByCityName("Radom");

            //Drukujemy nazwę miasta -> czy nam dobrze znalazło
            System.out.println("Miasto: " + cwd.getCityName());

            // printing the max./min. temperature
            System.out.println("Temperatura: " + cwd.getMainData().getTempMax()
                    + "/" + cwd.getMainData().getTempMin() + "\'C");
        }
        catch (APIException e) {
            System.out.println(e.getMessage());
        }

        launch(args);
    }
}
