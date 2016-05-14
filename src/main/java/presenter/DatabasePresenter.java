package presenter;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.access.DatabaseAccessObjectFactory;
import model.database.access.DecoratedBeehiveDAO;
import model.database.access.DecoratedStorageDAO;
import model.database.access.interfaces.DatabaseAccessObject;

import java.util.Date;
import java.util.List;

/**
 * Created by atticus on 4/30/16.
 */
public abstract class DatabasePresenter {
    DatabaseAccessObjectFactory databaseAccessObjectFactory;
    DatabaseAccessObject<Apiary> apiaryDao;
    DatabaseAccessObject<Beehive> beehiveDao;
    DatabaseAccessObject<Queen> queenDao;
    DecoratedBeehiveDAO decoratedBeehiveDao;
    DecoratedStorageDAO decoratedStorageDao;

    public JdbcPooledConnectionSource getConnectionSource() {
        return connectionSource;
    }

    JdbcPooledConnectionSource connectionSource;

    public DatabasePresenter(JdbcPooledConnectionSource connectionSource){
        databaseAccessObjectFactory = new DatabaseAccessObjectFactory(connectionSource);
        apiaryDao = databaseAccessObjectFactory.getDAO(Apiary.class);
        beehiveDao = databaseAccessObjectFactory.getDAO(Beehive.class);
        queenDao = databaseAccessObjectFactory.getDAO(Queen.class);
        decoratedBeehiveDao = new DecoratedBeehiveDAO(beehiveDao);
        decoratedStorageDao = new DecoratedStorageDAO(databaseAccessObjectFactory.getDAO(Storage.class));
        this.connectionSource = connectionSource;
    }

    //Konstruktory na sterydach - tworzą nowe obiekty oraz dodają je do bazy danych.
    public Beehive newBeehive(int apiaryId, double weight, int xCoordinate, int yCoordinate){
        Beehive beehive = new Beehive(apiaryId, weight, xCoordinate, yCoordinate);
        beehiveDao.create(beehive);
        return beehive;
    }

    public Beehive newBeehive(int apiaryId, double weight, int xCoordinate, int yCoordinate, boolean isInStorage, int queenId){
        Beehive beehive = new Beehive(apiaryId, weight, xCoordinate, yCoordinate, isInStorage, queenId);
        beehiveDao.create(beehive);
        return beehive;
    }

    public Apiary newApiary(String name, int xSize, int ySize){
        Apiary apiary = new Apiary(name, xSize, ySize);
        apiaryDao.create(apiary);
        return apiary;
    }

    public Queen newQueen(String race, String origin, Date date){
        Queen queen = new Queen(race, origin, date);
        queenDao.create(queen);
        return queen;
    }

    public List<Apiary> getApiaryList(){
        return apiaryDao.getAll();
    }

    public List<Beehive> getBeehiveList(){
        return decoratedBeehiveDao.getAll();
    }

    //Zwraca tylko ule dla których isInStorage = false, zapewnione w DatabaseHelperze
    public List<Beehive> getBeehivesFromApiary(Apiary apiary){
        return decoratedBeehiveDao.getBeehivesFromApiary(apiary);
    }

    //Zwraca pasiekę o konkretnym ID - nie wiem czy potrzebne
    public Apiary getApiaryWithID(int id){
        return apiaryDao.getWithID(id);
    }

    public Beehive getBeehiveWithID(int id){
        return (Beehive) decoratedBeehiveDao.getWithID(id);
    }

    public void updateApiary(Apiary apiary){
        apiaryDao.update(apiary);
    }

    public void updateBeehive(Beehive beehive){
       beehiveDao.update(beehive);
    }

    public Storage getStorage(){
        return decoratedStorageDao.getStorage();
    }

    public void updateStorage(Storage storage){
        decoratedStorageDao.update(storage);
    }



    //Ogólna metoda dodawania nowych, gotowych obiektów do bazy danych
    public void addNewObjectToDatabase(Object object){
        if (object.getClass() == Apiary.class) apiaryDao.create((Apiary) object);
        if (object.getClass() == Beehive.class) beehiveDao.create((Beehive) object);
        if (object.getClass() == Queen.class) queenDao.create((Queen) object);
    }

    public void updateQueen(Queen queen){
       queenDao.update(queen);
    }

    public List<Queen> getAllQueens(){
        return queenDao.getAll();
    }

    public Queen getQueenWithId(int queenId){
        return queenDao.getWithID(queenId);
    }

    public void deleteQueen(Queen queen){
        queenDao.remove(queen);
    }

    public List<Beehive> getBeehivesFromStorage(){
        return decoratedStorageDao.getBeehivesFromStorage();
    }
}
