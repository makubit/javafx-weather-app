package sample.Config;

import sample.Entities.City;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CityDataInitializer {
    public static void initializeCityData() {
        City radom = new City();
        radom.setCityName("Radom");
        radom.setCityLatitude(21.147);
        radom.setCityLongitude(51.402);

        City sosnowiec = new City();
        sosnowiec.setCityName("Sosnowiec");
        sosnowiec.setCityLatitude(19.104);
        sosnowiec.setCityLongitude(50.287);

        City kielce = new City();
        kielce.setCityName("Kielce");
        kielce.setCityLatitude(20.628);
        kielce.setCityLongitude(50.870);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(radom);
        entityManager.persist(sosnowiec);
        entityManager.persist(kielce);
        entityManager.getTransaction().commit();

        // Przykład zastosowania zapytania do bazy
        System.out.println(entityManager.createQuery("SELECT cityName FROM City").setMaxResults(3).getResultList());

        entityManager.close();
        entityManagerFactory.close();
    }
}
