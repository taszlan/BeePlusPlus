package presenter;

import java.util.List;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;

import general.exceptions.FactoryUnableToCreateDaoException;
import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.access.DatabaseAccessObjectFactory;
import model.database.access.DecoratedBeehiveDAO;
import model.database.access.DecoratedStorageDAO;
import model.database.access.interfaces.DatabaseAccessObject;

public class StoragePresenter {
	
	DatabaseAccessObjectFactory databaseAccessObjectFactory;
    DecoratedStorageDAO decoratedStorageDao;

    public JdbcPooledConnectionSource getConnectionSource() {
        return connectionSource;
    }

    JdbcPooledConnectionSource connectionSource;

    public StoragePresenter(JdbcPooledConnectionSource connectionSource){
        databaseAccessObjectFactory = new DatabaseAccessObjectFactory(connectionSource);
        try {
            decoratedStorageDao = new DecoratedStorageDAO(databaseAccessObjectFactory.getDAO(Storage.class));
        } catch (FactoryUnableToCreateDaoException e) {
            e.printStackTrace();
        }
        this.connectionSource = connectionSource;
    }

    public Storage getStorage(){
        return decoratedStorageDao.getStorage();
    }

    public void updateStorage(Storage storage){
        decoratedStorageDao.update(storage);
    }
    public List<Beehive> getBeehivesFromStorage(){
        return decoratedStorageDao.getBeehivesFromStorage();
    }
}
