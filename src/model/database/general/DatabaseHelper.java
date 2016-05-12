package model.database.general;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.general.interfaces.IDatabaseHelper;
import org.h2.jdbc.JdbcSQLException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atticus on 07.04.16.
 */
public class DatabaseHelper implements IDatabaseHelper {

    private final static boolean DATABASE_LOGGIGNG_ENABLED = true;
    private ConnectionSource connectionSource;
    private Dao<DatabaseVersion, Integer> databaseVersionDao;

    public DatabaseHelper(){
        try {
            String databaseUrl = "jdbc:h2:file:./beeplusplus";

            connectionSource = new JdbcConnectionSource(databaseUrl);
            databaseVersionDao= DaoManager.createDao(connectionSource, DatabaseVersion.class);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection() {
        try {
            connectionSource.close();
        } catch (Exception e){

        }
    }

    @Override
    public List<DatabaseVersion> getDatabaseVersions() throws JdbcSQLException{
        List<DatabaseVersion> databaseVersions = new ArrayList<>();
        try {
            databaseVersions = databaseVersionDao.queryForAll();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return databaseVersions;
    }


    @Override
    public void updateDatabaseVersion(DatabaseVersion databaseVersion){
        try {
            databaseVersionDao.update(databaseVersion);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void createDatabaseVersion(DatabaseVersion databaseVersion){
        try {
            databaseVersionDao.create(databaseVersion);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void createTables(){
        try {
            TableUtils.createTable(connectionSource, DatabaseVersion.class);
            TableUtils.createTable(connectionSource, Apiary.class);
            TableUtils.createTable(connectionSource, Beehive.class);
            TableUtils.createTable(connectionSource, Queen.class);
            TableUtils.createTable(connectionSource, Storage.class);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void clearTables(){
        try {
            TableUtils.clearTable(connectionSource, Apiary.class);
            TableUtils.clearTable(connectionSource, Beehive.class);
            TableUtils.clearTable(connectionSource, Storage.class);
            TableUtils.clearTable(connectionSource, Queen.class);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
