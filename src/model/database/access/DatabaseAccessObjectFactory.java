package model.database.access;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
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
    private ConnectionSource connectionSource;
    private GenericDAO<Apiary> apiaryGenericDAO;
    private GenericDAO<Beehive> beehiveGenericDAO;
    private GenericDAO<Queen> queenGenericDAO;
    private StorageDAO<Storage> storageDAO;

    private DatabaseAccessObjectFactory() {
        String databaseUrl = "jdbc:h2:file:./beeplusplus";

        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
        } catch (SQLException e){

        }
    }

    public static DatabaseAccessObjectFactory getInstance(){
        if (databaseAccessObjectFactory == null){
            databaseAccessObjectFactory = new DatabaseAccessObjectFactory();
        }
        return databaseAccessObjectFactory;
    }

    public DatabaseAccessObject getDAO(Class daoClass){
        if(daoClass == Apiary.class){
            if(apiaryGenericDAO == null){
                apiaryGenericDAO = new GenericDAO<Apiary>(connectionSource, daoClass);
            }
            return apiaryGenericDAO;
        }
        if(daoClass == Beehive.class){
            if(beehiveGenericDAO == null){
                beehiveGenericDAO = new GenericDAO<Beehive>(connectionSource, daoClass);
            }
            return beehiveGenericDAO;
        }
        if(daoClass == Queen.class){
            if(queenGenericDAO == null) {
                queenGenericDAO = new GenericDAO<Queen>(connectionSource, daoClass);
            }
            return queenGenericDAO;
        }
        if(daoClass == Storage.class){
            if(storageDAO == null){
                storageDAO = new StorageDAO<Storage>(connectionSource, daoClass);
            }
            return storageDAO;
        }
        return null;
    }
}
