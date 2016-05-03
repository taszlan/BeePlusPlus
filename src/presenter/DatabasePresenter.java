package presenter;

import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.DatabaseHelper;
import model.database.DatabaseHelperSingleton;
import model.database.interfaces.IDatabaseHelper;

import java.util.Date;
import java.util.List;

/**
 * Created by atticus on 4/30/16.
 */
public abstract class DatabasePresenter {
    IDatabaseHelper databaseHelper;

    public DatabasePresenter(){
        databaseHelper = DatabaseHelperSingleton.getDatabaseHelper();
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

    public void createNewQueen(String race, String origin, Date date){
        Queen queen = new Queen(race, origin, date);
        databaseHelper.createNewQueen(queen);
    }

    //Ogólna metoda dodawania nowych, gotowyc obiektów do bazy danych
    public void addNewObjectToDatabase(Object object){
        if (object.getClass() == Apiary.class) databaseHelper.createNewApiary((Apiary) object);
        if (object.getClass() == Beehive.class) databaseHelper.createNewBeehive((Beehive) object);
        if (object.getClass() == Queen.class) databaseHelper.createNewQueen((Queen) object);
    }

    public void updateQueen(Queen queen){
        databaseHelper.updateQueen(queen);
    }

    public List<Queen> getAllQueens(){
        return databaseHelper.getAllQueens();
    }

    public Queen getQueenWithId(int queenId){
        return databaseHelper.getQueenWithId(queenId);
    }

    public void deleteQueen(Queen queen){
        databaseHelper.deleteQueen(queen);
    }

    public List<Beehive> getBeehivesFromStorage(){
        return databaseHelper.getBeehivesFromStorage();
    }
}
