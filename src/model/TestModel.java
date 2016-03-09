package model;

/**
 * Created by atticus on 3/5/16.
 */
public class TestModel {
    private String welcomeString;

    public TestModel(){
        welcomeString = "Test commita bezposredniego, zakodowany na stałe w TestModel";
    }

    public String getWelcomeString() {
        return welcomeString;
    }

    public void setWelcomeString(String welcomeString) {
        this.welcomeString = welcomeString;
    }
}
