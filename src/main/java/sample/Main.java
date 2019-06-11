package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import sample.Config.CityDataInitializer;
import sample.Config.OWMApiConfig;
import sample.Entities.TestEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static sample.Config.CityDataInitializer.miasta;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{


        String fxmlFile = "/mainWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 600, 580);
        scene.getStylesheets().add("/style/style.css");

        stage.setTitle("Weather App");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        CityDataInitializer.initializeCityData();
        String miasto= (String) miasta.get(0);
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
            //System.out.println("Miasto: "+miasto);

            CurrentWeather cwd = owm.currentWeatherByCityName(miasto);
            HourlyWeatherForecast hwf=owm.hourlyWeatherForecastByCityName(miasto);
            //Drukujemy nazwę miasta -> czy nam dobrze znalazło
            System.out.println("Miasto: " + cwd.getCityName());

            // printing the max./min. temperature
            System.out.println("Temperatura: " + cwd.getMainData().getTempMax()
                    + "/" + cwd.getMainData().getTempMin() + "\'C");
            for (int i=0; i<9;i++) {
                System.out.println("Data: " + hwf.getDataList().get(i).getDateTimeText()+"\t Temperatura: "+hwf.getDataList().get(i).getMainData().getTemp()+"'C\tWilgotność powietrza: "+hwf.getDataList().get(i).getMainData().getHumidity()+"\tCiśnienie: "+hwf.getDataList().get(i).getMainData().getPressure()+"\tNiebo: "+ hwf.getDataList().get(i).getWeatherList().get(0).getMoreInfo());
            }
        }
        catch (APIException e) {
            System.out.println(e.getMessage());
        }

        launch(args);
    }
}
