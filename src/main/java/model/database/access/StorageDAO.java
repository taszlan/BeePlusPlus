package model.database.access;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import model.database.access.interfaces.DatabaseAccessObject;
import model.HasID;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atticus on 5/12/16.
 */
public class StorageDAO<T extends HasID> implements DatabaseAccessObject<T> {

    JdbcPooledConnectionSource connectionSource;
    private Dao<T, Integer> genericIntegerDao;

    public StorageDAO(JdbcPooledConnectionSource connectionSource, Class<T> classOfGeneric) {
        try {
            this.connectionSource = connectionSource;
            this.genericIntegerDao = DaoManager.createDao(connectionSource, classOfGeneric);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public JdbcPooledConnectionSource getConnectionSource(){
        return connectionSource;
    }

    @Override
    public void create(T t) {
        try {
            if(genericIntegerDao.queryForAll().size() == 0) {
                genericIntegerDao.create(t);
            } else {
                System.out.println("Storage already exists, skipping creation");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(T t) {
        try {
            genericIntegerDao.update(t);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List getAll() {
        List<T> list = new ArrayList<>();
        try {
            list =  genericIntegerDao.queryForAll();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void remove(T t) {
        try {
            genericIntegerDao.delete(t);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public T getWithID(int id) {
        T t = null;
        try {
            QueryBuilder<T, Integer> queryBuilder = genericIntegerDao.queryBuilder();
            PreparedQuery<T> preparedQuery = queryBuilder.where().eq(T.ID, id).prepare();
            t = genericIntegerDao.queryForFirst(preparedQuery);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public Dao getGenericDao() {
        return genericIntegerDao;
    }
}
