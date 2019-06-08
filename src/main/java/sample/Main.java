package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("/mainWindow.fxml"));
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TestEntity model = new TestEntity();
        model.setId(1);
        model.setDate("12.12.1990");
        model.setWeekDay("monday");

        entityManager.getTransaction().begin();
        entityManager.persist(model);
        entityManager.getTransaction().commit();

        System.out.println(entityManager.contains(model));

        launch(args);

        entityManager.close();
        entityManagerFactory.close();

    }
}
