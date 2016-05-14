package model.database.access;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import model.Beehive;
import model.Storage;
import model.database.access.interfaces.DatabaseAccessObject;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by atticus on 5/12/16.
 */
public class DecoratedStorageDAO extends GenericDAODecorator{

    public DecoratedStorageDAO(DatabaseAccessObject DAOToBeDecorated){
        super(DAOToBeDecorated);
    }

    public List<Beehive> getBeehivesFromStorage(){
        List<Beehive> beehiveList = null;
        try {
            //TODO: wypić 2 kawy, zrobić tak, żęby nie było brzydko
            DatabaseAccessObject<Beehive> beehiveDao = new DatabaseAccessObjectFactory(genericDAOToBeDecorated.getConnectionSource()).getDAO(Beehive.class);
            QueryBuilder<Beehive, Integer> queryBuilder = beehiveDao.getGenericDao().queryBuilder();
            PreparedQuery<Beehive> preparedQuery = queryBuilder.where().eq(Beehive.IS_IN_STORAGE, true).prepare();
            beehiveList = beehiveDao.getGenericDao().query(preparedQuery);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return beehiveList;
    }

    public Storage getStorage(){
        Storage storage = null;
        try {
            storage = (Storage) genericDAOToBeDecorated.getGenericDao().queryForAll().get(0);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return storage;
    }
}
