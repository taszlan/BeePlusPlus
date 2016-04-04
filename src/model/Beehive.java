package model;

/**
 * Created by atticus on 3/18/16.
 *
 * id - numer identyfikacyjny ula - unikatowy, domyślnie automatycznie nadawany przez bazę danych
 * apiaryNumber - numer pasieki, do której przypisany jest dany ul
 * weight - waga ula
 * beehiveCoordinates - współrzędne ula w danej pasiece
 * isInStorage - jeżeli true to ul jest w magazynie, czyli nie jest przypisany do konkretnej pasieki,
 *            Dodanie ula będzie się odbywało w 2 etapach: utworzenie nowego ula w magazynie i przeniesienie
 *            ula z magazynu do konkretenj pasieki.
 */
public class Beehive {
    private int id;
    private int apiaryNumber;
    private double weight;
    private int xCoordinate;
    private int yCoordinate;
    private boolean isInStorage;

    public Beehive(int id, int apiaryNumber, double weight, int xCoordinate, int yCoordinate){
        this.id = id;
        this.apiaryNumber = apiaryNumber;
        this.weight = weight;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.isInStorage = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApiaryNumber() {
        return apiaryNumber;
    }

    public void setApiaryNumber(int apiaryNumber) {
        this.apiaryNumber = apiaryNumber;
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
}
