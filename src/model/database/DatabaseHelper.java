package model.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Apiary;
import model.Beehive;

/**
 * Created by atticus on 07.04.16.
 */
public class DatabaseHelper {

    private final static boolean DATABASE_LOGGIGNG_ENABLED = true;
    private static DatabaseHelper databaseHelper;

    private DatabaseHelper(){
        try {
            String databaseUrl = "jdbc:h2:file:./beeplusplus";

            ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
            DatabaseCreator databaseCreator = new DatabaseCreator();
            databaseCreator.createDatabase(connectionSource);

            Dao<DatabaseVersion, Integer> databaseVersionDao= DaoManager.createDao(connectionSource, DatabaseVersion.class);
            Dao<Apiary, Integer> apiaryDao =  DaoManager.createDao(connectionSource, Apiary.class);
            Dao<Beehive, Integer> beehiveDao = DaoManager.createDao(connectionSource, Beehive.class);
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    public static DatabaseHelper getDatabaseHelper(){
        if(databaseHelper == null){
            databaseHelper = new DatabaseHelper();
        }

        return databaseHelper;
    }

}
