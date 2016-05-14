package model.database.access.interfaces;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import model.HasID;

import java.util.List;

/**
 * Created by atticus on 5/11/16.
 */
public interface DatabaseAccessObject<T extends HasID> {

    void create (T t);

    void update (T t);

    List<T> getAll ();

    void remove (T t);

    T getWithID(int id);

    Dao<T, Integer> getGenericDao();

    JdbcPooledConnectionSource getConnectionSource();
}
