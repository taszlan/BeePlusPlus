package model.database.access;

import com.j256.ormlite.dao.Dao;
import model.database.access.interfaces.DatabaseAccessObject;
import model.database.access.interfaces.HasID;

import java.util.List;

/**
 * Created by atticus on 5/12/16.
 */
public abstract class GenericDAODecorator implements DatabaseAccessObject{
    protected DatabaseAccessObject genericDAOToBeDecorated;

    public GenericDAODecorator (DatabaseAccessObject genericDAOToBeDecorated){
        this.genericDAOToBeDecorated = genericDAOToBeDecorated;
    }

    @Override
    public Dao getGenericDao(){
        return genericDAOToBeDecorated.getGenericDao();
    }

    @Override
    public void create(HasID hasID) {
        genericDAOToBeDecorated.create(hasID);
    }

    @Override
    public void update(HasID hasID) {
        genericDAOToBeDecorated.update(hasID);
    }

    @Override
    public List getAll() {
        return genericDAOToBeDecorated.getAll();
    }

    @Override
    public void remove(HasID hasID) {
        genericDAOToBeDecorated.remove(hasID);
    }

    @Override
    public HasID getWithID(int id) {
        return genericDAOToBeDecorated.getWithID(id);
    }
}
