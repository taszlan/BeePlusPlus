package general;

/**
 * Created by atticus on 5/13/16.
 */
public class Settings {
    String databaseUrl = "jdbc:h2:file:./beeplusplus;MULTI_THREADED=TRUE";

    public String getDatabaseUrl() {
        return databaseUrl;
    }
}
