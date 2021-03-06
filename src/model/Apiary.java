package model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by atticus on 3/18/16.
 *
 */
public class Apiary implements HasID{
    private static final int MAX_APIARY_X_SIZE = 100;
    private static final int MAX_APIARY_Y_SIZE = 100;

    @DatabaseField (generatedId = true, columnName = ID)
    private int apiaryID;
    @DatabaseField
    private String apiaryName;
    @DatabaseField
    private int xSize;
    @DatabaseField
    private int ySize;

    public Apiary(){
    }

    public Apiary(String apiaryName, int xSize, int ySize) {
        this.apiaryName = apiaryName;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public int getApiaryID() {
        return apiaryID;
    }

    public String getApiaryName() {
        return apiaryName;
    }

    public void setApiaryName(String apiaryName) {
        this.apiaryName = apiaryName;
    }

    @Override
    public String toString() {
        return "Apiary{" +
                "apiaryID=" + apiaryID +
                ", apiaryName='" + apiaryName + '\'' +
                ", xSize=" + xSize +
                ", ySize=" + ySize +
                '}';
    }
}
