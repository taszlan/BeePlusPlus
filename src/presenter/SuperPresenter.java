package presenter;

import model.Apiary;
import model.Beehive;
import model.database.DatabaseHelper;
import model.database.IDatabaseHelper;

import java.util.List;

/**
 * Created by atticus on 4/30/16.
 */
public class SuperPresenter {
    IDatabaseHelper databaseHelper;

    public SuperPresenter(){
        databaseHelper = DatabaseHelper.getDatabaseHelper();
    }

    public List<Apiary> getApiaryList(){
        return databaseHelper.getAllApiaries();
    }

    public List<Beehive> getBeehivesFromApiary(int apiaryId){
        return databaseHelper.getBeehivesFromApiary(apiaryId);
    }

    //Zwraca pasiekę o konkretnym ID
    public Apiary getApiaryWithID(int id){
        return databaseHelper.getApiaryWithId(id);
    }

    public Beehive getBeehiveWithID(int id){
        return databaseHelper.getBeehiveWithId(id);
    }

    public void createNewBeehive(){
        Beehive beehive = new Beehive();

    }

    public void createNewApiary(String name, int xSize, int ySize){
        Apiary apiary = new Apiary(name, xSize, ySize);
        databaseHelper.createNewApiary(apiary);
    }

    public void updateApiary(int apiaryID){

    }

    public void updateBeehive(int beehiveID){

    }
}
