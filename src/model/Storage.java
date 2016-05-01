package model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by atticus on 5/1/16.
 */
public class Storage {
    @DatabaseField(id = true)
    int storage_id;
    @DatabaseField
    int numberOfFrames;
    @DatabaseField
    int numberOfBodies;
    @DatabaseField
    int numberOfBottoms;
    @DatabaseField
    int numberOfRoofs;

    public Storage(){

    }

    public Storage(int storage_id, int numberOfFrames, int numberOfBodies, int numberOfBottoms, int numberOfRoofs) {
        this.storage_id = storage_id;
        this.numberOfFrames = numberOfFrames;
        this.numberOfBodies = numberOfBodies;
        this.numberOfBottoms = numberOfBottoms;
        this.numberOfRoofs = numberOfRoofs;
    }

    public int getStorage_id() {
        return storage_id;
    }

    public void setStorage_id(int storage_id) {
        this.storage_id = storage_id;
    }

    public int getNumberOfFrames() {
        return numberOfFrames;
    }

    public void setNumberOfFrames(int numberOfFrames) {
        this.numberOfFrames = numberOfFrames;
    }

    public int getNumberOfBodies() {
        return numberOfBodies;
    }

    public void setNumberOfBodies(int numberOfBodies) {
        this.numberOfBodies = numberOfBodies;
    }

    public int getNumberOfBottoms() {
        return numberOfBottoms;
    }

    public void setNumberOfBottoms(int numberOfBottoms) {
        this.numberOfBottoms = numberOfBottoms;
    }

    public int getNumberOfRoofs() {
        return numberOfRoofs;
    }

    public void setNumberOfRoofs(int numberOfRoofs) {
        this.numberOfRoofs = numberOfRoofs;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "numberOfFrames=" + numberOfFrames +
                ", numberOfBodies=" + numberOfBodies +
                ", numberOfBottoms=" + numberOfBottoms +
                ", numberOfRoofs=" + numberOfRoofs +
                '}';
    }
}
