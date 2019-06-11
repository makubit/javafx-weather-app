package sample;

import javafx.application.Application;
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

    @Override
    public void start(Stage primaryStage) throws Exception{
        CityDataInitializer.initializeCityData();

        Parent root = FXMLLoader.load(getClass().getResource("/mainWindow.fxml"));
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(new Scene(root, 727.0,587.0));
        primaryStage.show();

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
