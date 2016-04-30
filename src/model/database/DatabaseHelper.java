package model.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import model.Apiary;
import model.Beehive;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atticus on 07.04.16.
 */
public class DatabaseHelper implements IDatabaseHelper {

    private final static boolean DATABASE_LOGGIGNG_ENABLED = true;
    private static IDatabaseHelper IDatabaseHelper;
    private ConnectionSource connectionSource;
    Dao<DatabaseVersion, Integer> databaseVersionDao;
    Dao<Apiary, Integer> apiaryDao;
    Dao<Beehive, Integer> beehiveDao;

    private DatabaseHelper(){
        try {
            String databaseUrl = "jdbc:h2:file:./beeplusplus";

            connectionSource = new JdbcConnectionSource(databaseUrl);
            DatabaseCreator databaseCreator = new DatabaseCreator();
            databaseCreator.createDatabase(connectionSource);

            databaseVersionDao= DaoManager.createDao(connectionSource, DatabaseVersion.class);
            apiaryDao =  DaoManager.createDao(connectionSource, Apiary.class);
            beehiveDao = DaoManager.createDao(connectionSource, Beehive.class);
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
    public List<Beehive> getBeehivesFromApiary(int apiaryId){
        List<Beehive> beehiveList= new ArrayList<>();
        try {
            QueryBuilder<Beehive, Integer> beehiveIntegerQueryBuilder= beehiveDao.queryBuilder();
            PreparedQuery<Beehive> preparedQuery = beehiveIntegerQueryBuilder.where().eq(Beehive.APIARY_ID, apiaryId).prepare();
            beehiveList = beehiveDao.query(preparedQuery);
        } catch (SQLException e){
            e.printStackTrace();
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


    public static IDatabaseHelper getDatabaseHelper(){
        if(IDatabaseHelper == null){
            IDatabaseHelper = new DatabaseHelper();
        }

        return IDatabaseHelper;
    }

}
