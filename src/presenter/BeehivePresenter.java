package presenter;
import java.util.List;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;

import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.access.DatabaseAccessObjectFactory;
import model.database.access.interfaces.DatabaseAccessObject;
import model.database.access.DecoratedBeehiveDAO;
import model.database.access.DecoratedStorageDAO;
public class BeehivePresenter {
	DatabaseAccessObjectFactory databaseAccessObjectFactory;
   
    DatabaseAccessObject<Beehive> beehiveDao;
    DatabaseAccessObject<Apiary> apiaryDao;
    DecoratedBeehiveDAO decoratedBeehiveDao;
   

    public JdbcPooledConnectionSource getConnectionSource() {
        return connectionSource;
    }

    JdbcPooledConnectionSource connectionSource;

    public BeehivePresenter(JdbcPooledConnectionSource connectionSource){
    	   databaseAccessObjectFactory = new DatabaseAccessObjectFactory(connectionSource);
           
           beehiveDao = databaseAccessObjectFactory.getDAO(Beehive.class);
           
           decoratedBeehiveDao = new DecoratedBeehiveDAO(beehiveDao);
           
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

 
    public List<Beehive> getBeehiveList(){
        return decoratedBeehiveDao.getAll();
    }

    //Zwraca tylko ule dla których isInStorage = false, zapewnione w DatabaseHelperze
    public List<Beehive> getBeehivesFromApiary(Apiary apiary){
        return decoratedBeehiveDao.getBeehivesFromApiary(apiary);
    }
    
    public Beehive getBeehiveWithID(int id){
        return (Beehive) decoratedBeehiveDao.getWithID(id);
    }
    public void updateBeehive(Beehive beehive){
        beehiveDao.update(beehive);
     }
   

    //Ogólna metoda dodawania nowych, gotowych obiektów do bazy danych
    public void addNewObjectToDatabase(Object object){
       
        if (object.getClass() == Beehive.class) beehiveDao.create((Beehive) object);
        
    }

}
