package model.database.access;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import general.Settings;
import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import general.exceptions.FactoryUnableToCreateDaoException;
import model.*;
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
    }

    public DatabaseAccessObject getDAO(Class daoClass) throws FactoryUnableToCreateDaoException{
        if(daoClass == Apiary.class){
            return new GenericDAO<Apiary>(connectionSource, daoClass);
        }
        if(daoClass == Beehive.class){
            return new GenericDAO<Beehive>(connectionSource, daoClass);
        }
        if(daoClass == Queen.class){
            return new GenericDAO<Queen>(connectionSource, daoClass);
        }
        if(daoClass == Storage.class){
            return new StorageDAO<Storage>(connectionSource, daoClass);
        }
        if(daoClass == InternalEvent.class){
            return new GenericDAO<InternalEvent>(connectionSource, daoClass);
        }
        throw new FactoryUnableToCreateDaoException();
    }
}
