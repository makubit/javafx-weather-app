package sample;

/* Klasa na użycie Singletona */
public class OWMApiConfig {
    private static OWMApiConfig owmApiConfig= null;
    private static String apiKey = "07d1211ab3ab84589d842c10d03fe031";

    //07d1211ab3ab84589d842c10d03fe031

    public static OWMApiConfig getInstance()
    {
        if (owmApiConfig == null)
            owmApiConfig = new OWMApiConfig();

        return owmApiConfig;
    }

    public String getApiKey() {
        return apiKey;
    }
}
