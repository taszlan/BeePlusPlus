package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by atticus on 3/18/16.
 *
 * id - numer identyfikacyjny ula - unikatowy, domyślnie automatycznie nadawany przez bazę danych
 * apiaryId - numer pasieki, do której przypisany jest dany ul
 * weight - waga ula
 * beehiveCoordinates - współrzędne ula w danej pasiece
 * isInStorage - jeżeli true to ul jest w magazynie, czyli nie jest przypisany do konkretnej pasieki,
 *            Dodanie ula będzie się odbywało w 2 etapach: utworzenie nowego ula w magazynie i przeniesienie
 *            ula z magazynu do konkretenj pasieki.
 */
@DatabaseTable(tableName = "beehives")
public class Beehive implements HasID{
    public static final String APIARY_ID = "APIARY_ID";
    public static final String IS_IN_STORAGE = "IS_IN_STORAGE";

    @DatabaseField(generatedId = true, columnName = ID)
    private int database_id;
    @DatabaseField(columnName = APIARY_ID)
    private int apiaryId;
    @DatabaseField
    private int queenId;
    @DatabaseField
    private double weight;
    @DatabaseField
    private int xCoordinate;
    @DatabaseField
    private int yCoordinate;
    @DatabaseField(columnName = IS_IN_STORAGE)
    private boolean isInStorage;

    public Beehive(){

    }

    public Beehive(int apiaryId, double weight, int xCoordinate, int yCoordinate){
        this.apiaryId = apiaryId;
        this.weight = weight;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.isInStorage = true;
    }

    public Beehive(int apiaryId, double weight, int xCoordinate, int yCoordinate, boolean isInStorage, int queenId){
        this.apiaryId = apiaryId;
        this.weight = weight;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.isInStorage = isInStorage;
        this.queenId = queenId;
    }

    public int getQueenId() {
        return queenId;
    }

    public void setQueenId(int queenId) {
        this.queenId = queenId;
    }

    public int getDatabaseId() {
        return database_id;
    }

    public void setId(int id) {
        this.database_id = id;
    }
    
    

    public int getApiaryId() {
        return apiaryId;
    }

    public void setApiaryId(int apiaryId) {
        this.apiaryId = apiaryId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getBeehiveXCoordinate(){
        return xCoordinate;
    }

    public int getBeehiveYCoordinate(){
        return yCoordinate;
    }

    public void setBeehiveCoordinates(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public boolean isInStorage() {
        return this.isInStorage;
    }

    public void setInStorage(boolean inStorage) {
        this.isInStorage = inStorage;
    }

    @Override
    public String toString() {
        return "Beehive{" +
                "database_id=" + database_id +
                ", apiaryId=" + apiaryId +
                ", queenId=" + queenId +
                ", weight=" + weight +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                ", isInStorage=" + isInStorage +
                '}';
    }
}
