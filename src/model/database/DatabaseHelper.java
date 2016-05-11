package model.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Log;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.interfaces.IDatabaseHelper;
import org.h2.jdbc.JdbcSQLException;

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

    public DatabaseHelper(){
        try {
            String databaseUrl = "jdbc:h2:file:./beeplusplus";

            connectionSource = new JdbcConnectionSource(databaseUrl);

            databaseVersionDao= DaoManager.createDao(connectionSource, DatabaseVersion.class);
            apiaryDao =  DaoManager.createDao(connectionSource, Apiary.class);
            beehiveDao = DaoManager.createDao(connectionSource, Beehive.class);
            storageDao = DaoManager.createDao(connectionSource, Storage.class);
            queenDao = DaoManager.createDao(connectionSource, Queen.class);
        } catch (Exception e){
            e.printStackTrace();
        }
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

    @Override
    public List<DatabaseVersion> getDatabaseVersions() throws JdbcSQLException{
        List<DatabaseVersion> databaseVersions = new ArrayList<>();
        try {
            databaseVersions = databaseVersionDao.queryForAll();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return databaseVersions;
    }


    @Override
    public void updateDatabaseVersion(DatabaseVersion databaseVersion){
        try {
            databaseVersionDao.update(databaseVersion);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void createDatabaseVersion(DatabaseVersion databaseVersion){
        try {
            databaseVersionDao.create(databaseVersion);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void createTables(){
        try {
            TableUtils.createTable(connectionSource, DatabaseVersion.class);
            TableUtils.createTable(connectionSource, Apiary.class);
            TableUtils.createTable(connectionSource, Beehive.class);
            TableUtils.createTable(connectionSource, Queen.class);
            TableUtils.createTable(connectionSource, Storage.class);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void createStorage(Storage storage){
        try {
            storageDao.create(storage);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void clearTables(){
        try {
            TableUtils.clearTable(connectionSource, Apiary.class);
            TableUtils.clearTable(connectionSource, Beehive.class);
            TableUtils.clearTable(connectionSource, Storage.class);
            TableUtils.clearTable(connectionSource, Queen.class);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
