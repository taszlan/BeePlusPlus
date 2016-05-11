package model.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Log;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.interfaces.IDatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by atticus on 07.04.16.
 */
public class DatabaseHelper implements model.database.interfaces.IDatabaseHelper {

    private final static boolean DATABASE_LOGGIGNG_ENABLED = true;
    private static IDatabaseHelper IDatabaseHelper;
    private ConnectionSource connectionSource;
    private Dao<DatabaseVersion, Integer> databaseVersionDao;
    private Dao<Apiary, Integer> apiaryDao;
    private Dao<Beehive, Integer> beehiveDao;
    private Dao<Storage, Integer> storageDao;
    private Dao<Queen, Integer> queenDao;
    private DatabaseCreator databaseCreator;

    public DatabaseHelper(){
        try {
            String databaseUrl = "jdbc:h2:file:./beeplusplus";

            connectionSource = new JdbcConnectionSource(databaseUrl);
            databaseCreator = new DatabaseCreator();
            databaseCreator.createDatabase(connectionSource);

            databaseVersionDao= DaoManager.createDao(connectionSource, DatabaseVersion.class);
            apiaryDao =  DaoManager.createDao(connectionSource, Apiary.class);
            beehiveDao = DaoManager.createDao(connectionSource, Beehive.class);
            storageDao = DaoManager.createDao(connectionSource, Storage.class);
            queenDao = DaoManager.createDao(connectionSource, Queen.class);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printDatabaseLog(){
        databaseCreator.printLog();
    }


    @Override
    public void createNewApiary(Apiary apiary){
        try {
            apiaryDao.create(apiary);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void createNewQueen(Queen queen){
        try {
            queenDao.create(queen);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateQueen(Queen queen) {
        try {
            queenDao.update(queen);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Queen> getAllQueens() {
        List<Queen> queenList = new ArrayList<>();
        try {
            queenList =  queenDao.queryForAll();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return queenList;
    }

    @Override
    public Queen getQueenWithId(int queenId) {
        Queen queen = new Queen();
        try {
            QueryBuilder<Queen, Integer> queenIntegerQueryBuilder= queenDao.queryBuilder();
            PreparedQuery<Queen> preparedQuery = queenIntegerQueryBuilder.where().eq(Queen.QUEEN_ID, queenId).prepare();
            queen = queenDao.queryForFirst(preparedQuery);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return queen;
    }

    @Override
    public void deleteQueen(Queen queen) {
        try {
            queenDao.delete(queen);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateApiary(Apiary apiary){
        try {
            apiaryDao.update(apiary);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Kasuje pasiekę i przenosi wszystkie ule do Storage
    @Override
    public void deleteApiary(Apiary apiary){
        try {
            List<Beehive> beehiveList = getBeehivesFromApiary(apiary);
            for (Beehive beehive : beehiveList){
                beehive.setInStorage(true);
                updateBeehive(beehive);
            }
            apiaryDao.delete(apiary);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Storage getStorage(){
        Storage storage = null;
        try {
            storage = storageDao.queryForAll().get(0);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return storage;
    }

    @Override
    public void updateStorage(Storage storage){
        try {
            storageDao.update(storage);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Beehive> getBeehivesFromStorage(){
        List<Beehive> beehiveList= new ArrayList<>();
        try {
            QueryBuilder<Beehive, Integer> beehiveIntegerQueryBuilder= beehiveDao.queryBuilder();
            PreparedQuery<Beehive> preparedQuery = beehiveIntegerQueryBuilder.where().eq(Beehive.IS_IN_STORAGE, true).prepare();
            beehiveList = beehiveDao.query(preparedQuery);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return beehiveList;
    }

    @Override
    public void updateBeehive(Beehive beehive){
        try {
            beehiveDao.update(beehive);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void createNewBeehive(Beehive beehive) {
        try {
            beehiveDao.create(beehive);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBeehive(Beehive beehive){
        try {
            beehiveDao.delete(beehive);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Beehive> getBeehivesFromApiary(Apiary apiary){
        List<Beehive> beehiveList= new ArrayList<>();
        try {
            QueryBuilder<Beehive, Integer> beehiveIntegerQueryBuilder= beehiveDao.queryBuilder();
            PreparedQuery<Beehive> preparedQuery = beehiveIntegerQueryBuilder.where().eq(Beehive.APIARY_ID, apiary.getApiaryID()).prepare();
            beehiveList = beehiveDao.query(preparedQuery);
        } catch (SQLException e){
            e.printStackTrace();
        }

        //Usuwa z listy Ule dla których isInStorage = true;
        Iterator<Beehive> beehiveIterator = beehiveList.iterator();
        Beehive beehive;
        while(beehiveIterator.hasNext()){
            beehive = beehiveIterator.next();
            if(beehive.isInStorage()){
                beehiveIterator.remove();
            }
        }
        return beehiveList;
    }

    @Override
    public List<Apiary> getAllApiaries(){
        List<Apiary> apiaryList = new ArrayList<>();
        try {
            apiaryList =  apiaryDao.queryForAll();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return apiaryList;
    }

    @Override
    public List<Beehive> getAllBeehives() {
        List<Beehive> beehiveList = new ArrayList<>();
        try {
            beehiveList =  beehiveDao.queryForAll();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return beehiveList;
    }

    @Override
    public Apiary getApiaryWithId(int apiaryId){
        Apiary apiary = new Apiary();
        try {
            QueryBuilder<Apiary, Integer> apiaryIntegerQueryBuilder= apiaryDao.queryBuilder();
            PreparedQuery<Apiary> preparedQuery = apiaryIntegerQueryBuilder.where().eq(Apiary.APIARY_ID, apiaryId).prepare();
            apiary = apiaryDao.queryForFirst(preparedQuery);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return apiary;
    }

    @Override
    public Beehive getBeehiveWithId(int beehiveId){
        Beehive beehive = new Beehive();
        try {
            QueryBuilder<Beehive, Integer> beehiveIntegerQueryBuilder= beehiveDao.queryBuilder();
            PreparedQuery<Beehive> preparedQuery = beehiveIntegerQueryBuilder.where().eq(Beehive.BEEHIVE_ID, beehiveId).prepare();
            beehive = beehiveDao.queryForFirst(preparedQuery);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return beehive;
    }

    @Override
    public void closeConnection() {
        try {
            connectionSource.close();
        } catch (Exception e){

        }
    }
}
