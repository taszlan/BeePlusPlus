package model.database.access;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import general.Settings;
import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.access.interfaces.DatabaseAccessObject;

import java.sql.SQLException;

/**
 * Created by atticus on 5/11/16.
 */
public class DatabaseAccessObjectFactory {

    private static DatabaseAccessObjectFactory databaseAccessObjectFactory;
    private GenericDAO<Apiary> apiaryGenericDAO;
    private GenericDAO<Beehive> beehiveGenericDAO;
    private GenericDAO<Queen> queenGenericDAO;
    private StorageDAO<Storage> storageDAO;
    private Settings settings;
    private String databaseUrl;
    private JdbcPooledConnectionSource connectionSource;

    public DatabaseAccessObjectFactory(JdbcPooledConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
//        try {
//            connectionSource = new JdbcConnectionSource(databaseUrl);
//        } catch (SQLException e){
//
//        }
    }

//    public static DatabaseAccessObjectFactory getInstance(){
//        if (databaseAccessObjectFactory == null){
//            databaseAccessObjectFactory = new DatabaseAccessObjectFactory();
//        }
//        return databaseAccessObjectFactory;
//    }

    public DatabaseAccessObject getDAO(Class daoClass){
        if(daoClass == Apiary.class){
//            if(apiaryGenericDAO == null){
//                apiaryGenericDAO = new GenericDAO<Apiary>(connectionSource, daoClass);
//            }
            return new GenericDAO<Apiary>(connectionSource, daoClass);
        }
        if(daoClass == Beehive.class){
//            if(beehiveGenericDAO == null){
//                beehiveGenericDAO = new GenericDAO<Beehive>(connectionSource, daoClass);
//            }
            return new GenericDAO<Beehive>(connectionSource, daoClass);
        }
        if(daoClass == Queen.class){
//            if(queenGenericDAO == null) {
//                queenGenericDAO = new GenericDAO<Queen>(connectionSource, daoClass);
//            }
            return new GenericDAO<Queen>(connectionSource, daoClass);
        }
        if(daoClass == Storage.class){
//            if(storageDAO == null){
//                storageDAO = new StorageDAO<Storage>(connectionSource, daoClass);
//            }
            return new StorageDAO<Storage>(connectionSource, daoClass);
        }
        return null;
    }
}
