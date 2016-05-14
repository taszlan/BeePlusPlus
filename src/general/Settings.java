package general;
import java.util.Arrays;
import java.util.List;

/**
 * Created by atticus on 5/13/16.
 */
public class Settings {

    List<String> listOfEmails = Arrays.asList("beekeeper_one@example.com", "beekeeper_two@example.com");
    String databaseUrl = "jdbc:h2:file:./beeplusplus;MULTI_THREADED=TRUE";
    boolean guiMode = false;
    boolean databaseMode = true;
    boolean deleteEventsFromGoogle = false;
    boolean alwaysClearDatabase = true;
    boolean databaseLoggingEnabled = true;

    public boolean isGuiMode() {
        return guiMode;
    }

    public boolean isDatabaseMode() {
        return databaseMode;
    }

    public boolean isDeleteEventsFromGoogle() {
        return deleteEventsFromGoogle;
    }

    public boolean isAlwaysClearDatabase() {
        return alwaysClearDatabase;
    }

    public boolean isDatabaseLoggingEnabled() {
        return databaseLoggingEnabled;
    }

    public List<String> getListOfEmails() {
        return listOfEmails;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }
}
