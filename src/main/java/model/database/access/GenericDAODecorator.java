package model.database.access;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import model.database.access.interfaces.DatabaseAccessObject;
import model.HasID;

import java.util.List;

/**
 * Created by atticus on 5/12/16.
 */
public abstract class GenericDAODecorator<T extends HasID> implements DatabaseAccessObject<T>{
    protected DatabaseAccessObject<T> genericDAOToBeDecorated;

    public GenericDAODecorator (DatabaseAccessObject<T> genericDAOToBeDecorated){
        this.genericDAOToBeDecorated = genericDAOToBeDecorated;
    }

    @Override
    public Dao<T,Integer> getGenericDao(){
        return genericDAOToBeDecorated.getGenericDao();
    }

    @Override
    public void create(T hasID) {
        genericDAOToBeDecorated.create(hasID);
    }

    @Override
    public JdbcPooledConnectionSource getConnectionSource(){
        return genericDAOToBeDecorated.getConnectionSource();
    }

    @Override
    public void update(T hasID) {
        genericDAOToBeDecorated.update(hasID);
    }

    @Override
    public List<T> getAll() {
        return genericDAOToBeDecorated.getAll();
    }

    @Override
    public void remove(T hasID) {
        genericDAOToBeDecorated.remove(hasID);
    }

    @Override
    public T getWithID(int id) {
        return genericDAOToBeDecorated.getWithID(id);
    }
}
