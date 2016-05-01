package presenter;

import model.Apiary;
import model.Beehive;
import model.Storage;
import model.database.DatabaseHelper;
import model.database.interfaces.IDatabaseHelper;

import java.util.List;

/**
 * Created by atticus on 4/30/16.
 */
public class DatabasePresenter {
    IDatabaseHelper databaseHelper;

    public DatabasePresenter(){
        databaseHelper = DatabaseHelper.getDatabaseHelper();
    }

    public List<Apiary> getApiaryList(){
        return databaseHelper.getAllApiaries();
    }

    public List<Beehive> getBeehiveList(){
        return databaseHelper.getAllBeehives();
    }

    //Zwraca tylko ule dla których isInStorage = false, zapewnione w DatabaseHelperze
    public List<Beehive> getBeehivesFromApiary(Apiary apiary){
        return databaseHelper.getBeehivesFromApiary(apiary);
    }

    //Zwraca pasiekę o konkretnym ID - nie wiem czy potrzebne
    public Apiary getApiaryWithID(int id){
        return databaseHelper.getApiaryWithId(id);
    }

    public Beehive getBeehiveWithID(int id){
        return databaseHelper.getBeehiveWithId(id);
    }

    public void createNewBeehive(){
        Beehive beehive = new Beehive();
        databaseHelper.createNewBeehive(beehive);
    }

    public void createNewApiary(String name, int xSize, int ySize){
        Apiary apiary = new Apiary(name, xSize, ySize);
        databaseHelper.createNewApiary(apiary);
    }

    public void updateApiary(Apiary apiary){
        databaseHelper.updateApiary(apiary);
    }

    public void updateBeehive(Beehive beehive){
        databaseHelper.updateBeehive(beehive);
    }

    public Storage getStorage(){
        return databaseHelper.getStorage();
    }

    public void updateStorage(Storage storage){
        databaseHelper.updateStorage(storage);
    }

}
