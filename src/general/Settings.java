package general;
import java.util.Arrays;
import java.util.List;

/**
 * Created by atticus on 5/13/16.
 */
public class Settings {

    List<String> listOfEmails = Arrays.asList("beekeeper_one@example.com", "beekeeper_two@example.com");

    public List<String> getListOfEmails() {
        return listOfEmails;
    }

    String databaseUrl = "jdbc:h2:file:./beeplusplus;MULTI_THREADED=TRUE";

    public String getDatabaseUrl() {
        return databaseUrl;
    }
}
