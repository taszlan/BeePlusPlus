package model.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Apiary;
import model.Beehive;
import org.h2.jdbc.JdbcSQLException;

import java.util.List;
import java.util.Random;

/**
 * Created by atticus on 4/30/16.
 */
public class DatabaseCreator {
    private final static boolean DATABASE_LOGGIGNG_ENABLED = true;

    public void createDatabase(ConnectionSource connectionSource){
        boolean databaseVersionHasChanged = false;

        try {
            Dao<DatabaseVersion, Integer> databaseVersionDao = DaoManager.createDao(connectionSource, DatabaseVersion.class);

            //Sprawdza czy istnieje tabela DatabaseVersion, nieszczególnie piękne ale działa
            try{
                databaseVersionDao.queryForId(0);
            } catch (JdbcSQLException e) {
                if (DATABASE_LOGGIGNG_ENABLED) System.out.println("Creating DatabaseVersion table");

                TableUtils.createTable(connectionSource, DatabaseVersion.class);
                DatabaseVersion databaseVersion = new DatabaseVersion(0, 0);
                databaseVersionDao.create(databaseVersion);
            }

            DatabaseVersion databaseVersion;
            databaseVersion = databaseVersionDao.queryForId(0);

            //Tworzenie podstawowych tabel - Apiary i Beehive
            if (databaseVersion.getVersion() < 1){
                if (DATABASE_LOGGIGNG_ENABLED) System.out.println("Creating Apiary and Beehive tables");

                TableUtils.createTable(connectionSource, Apiary.class);
                TableUtils.createTable(connectionSource, Beehive.class);

                databaseVersion.setVersion(1);
                databaseVersionHasChanged = true;
            }

            if (databaseVersion.getVersion() < 2) {
                System.out.println("Database version < 2");
                databaseVersionHasChanged = true;
            }

            databaseVersionDao.update(databaseVersion);
            if(databaseVersionHasChanged) fillDatabaseWithExampleData(connectionSource);
            connectionSource.close();

        } catch (Exception e){
            System.out.println("SQLException");
            e.printStackTrace();
            try{
                connectionSource.close();
            } catch (Exception e1){

            }
        } finally {

        }
    }

    public void fillDatabaseWithExampleData(ConnectionSource connectionSource){
        try {
            if (DATABASE_LOGGIGNG_ENABLED) System.out.println("----CREATING-SAMPLE-OBJECTS------");
            Dao<Apiary, Integer> apiaryDao =  DaoManager.createDao(connectionSource, Apiary.class);
            Dao<Beehive, Integer> beehiveDao = DaoManager.createDao(connectionSource, Beehive.class);
            Random generator = new Random();

            //Najpierw trzeba wyczyścić tabele
            TableUtils.clearTable(connectionSource, Apiary.class);
            TableUtils.clearTable(connectionSource, Beehive.class);

            //Potem dodać testowe obiekty - Apiary
            Apiary apiary1 = new Apiary("Pierwsza Pasieka", 100, 100);
            Apiary apiary2 = new Apiary("Druga Pasieka", 100, 100);
            apiaryDao.create(apiary1);
            apiaryDao.create(apiary2);

            List<Apiary> listOfApiaries = apiaryDao.queryForAll();

            //Testowe Beehives
            Beehive beehive1 = new Beehive(listOfApiaries.get(0).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false);
            Beehive beehive2 = new Beehive(listOfApiaries.get(0).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false);
            Beehive beehive3 = new Beehive(listOfApiaries.get(0).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false);

            beehiveDao.create(beehive1);
            beehiveDao.create(beehive2);
            beehiveDao.create(beehive3);

            Beehive beehive4 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false);
            Beehive beehive5 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false);
            Beehive beehive6 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false);

            beehiveDao.create(beehive4);
            beehiveDao.create(beehive5);
            beehiveDao.create(beehive6);

            if(DATABASE_LOGGIGNG_ENABLED) {
                List<Beehive> listOfBeehives = beehiveDao.queryForAll();
                System.out.println("----DATABASE-FILLED-WITH-SAMPLE-DATA-------");
                System.out.println("------LISTING-DATABASE-CONTENTS------------");

                for (Apiary a : listOfApiaries) {
                    System.out.println(a);
                }
                for (Beehive b : listOfBeehives) {
                    System.out.println(b);
                }
            }

        } catch (Exception e){

        }
    }
}
