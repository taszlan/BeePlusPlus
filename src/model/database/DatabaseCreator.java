package model.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Apiary;
import model.Beehive;
import model.Queen;
import model.Storage;
import model.database.interfaces.IDatabaseHelper;
import org.h2.jdbc.JdbcSQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by atticus on 4/30/16.
 */
public class DatabaseCreator {
    private final static boolean DATABASE_LOGGIGNG_ENABLED = false;

    public void createDatabase(ConnectionSource connectionSource){
        boolean databaseVersionHasChanged = false;

        try {
            Dao<DatabaseVersion, Integer> databaseVersionDao = DaoManager.createDao(connectionSource, DatabaseVersion.class);
            Dao<Storage, Integer> storageDao = DaoManager.createDao(connectionSource, Storage.class);

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

                TableUtils.createTable(connectionSource, Queen.class);
                TableUtils.createTable(connectionSource, Storage.class);
                Storage storage = new Storage(0, 0, 0, 0, 0);
                storageDao.create(storage);

                databaseVersion.setVersion(2);
                databaseVersionHasChanged = true;
            }

            if (databaseVersion.getVersion() < 3) {
                System.out.println("Database version < 3");
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
            Dao<Storage, Integer> storageDao = DaoManager.createDao(connectionSource, Storage.class);
            Dao<Queen, Integer> queenDao = DaoManager.createDao(connectionSource, Queen.class);
            Random generator = new Random();

            //Najpierw trzeba wyczyścić tabele
            TableUtils.clearTable(connectionSource, Apiary.class);
            TableUtils.clearTable(connectionSource, Beehive.class);
            TableUtils.clearTable(connectionSource, Storage.class);
            TableUtils.clearTable(connectionSource, Queen.class);

            //Potem dodać testowe obiekty - Apiary
            Apiary apiary1 = new Apiary("Pierwsza Pasieka", 100, 100);
            Apiary apiary2 = new Apiary("Druga Pasieka", 100, 100);
            apiaryDao.create(apiary1);
            apiaryDao.create(apiary2);

            List<Apiary> listOfApiaries = apiaryDao.queryForAll();

            //Storage
            Storage storage = new Storage(0,0,0,0,0);
            storageDao.create(storage);

            //Testowe Queens
            Queen queen;
            for (int i = 0; i < 6; i++){
                queen = new Queen("Królowa nr " + i, "Miejsce " +i, new Date());
                queenDao.create(queen);
            }


            //Testowe Beehives
            Beehive beehive1 = new Beehive(listOfApiaries.get(0).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, queenDao.queryForAll().get(0).getQueenId());
            Beehive beehive2 = new Beehive(listOfApiaries.get(0).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, queenDao.queryForAll().get(1).getQueenId());
            Beehive beehive3 = new Beehive(listOfApiaries.get(0).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, queenDao.queryForAll().get(2).getQueenId());

            beehiveDao.create(beehive1);
            beehiveDao.create(beehive2);
            beehiveDao.create(beehive3);

            Beehive beehive4 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, queenDao.queryForAll().get(3).getQueenId());
            Beehive beehive5 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, queenDao.queryForAll().get(4).getQueenId());
            Beehive beehive6 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), false, queenDao.queryForAll().get(5).getQueenId());

            beehiveDao.create(beehive4);
            beehiveDao.create(beehive5);
            beehiveDao.create(beehive6);

            Beehive beehive7 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), true, 0);
            Beehive beehive8 = new Beehive(listOfApiaries.get(1).getApiaryID(), 10*generator.nextDouble(), generator.nextInt(10), generator.nextInt(10), true, 0);

            beehiveDao.create(beehive7);
            beehiveDao.create(beehive8);

            Storage storage1 = storageDao.queryForAll().get(0);
            storage1.setNumberOfBodies(generator.nextInt(100));
            storage1.setNumberOfBottoms(generator.nextInt(100));
            storage1.setNumberOfFrames(generator.nextInt(100));
            storage1.setNumberOfRoofs(generator.nextInt(100));
            storageDao.update(storage1);

        } catch (Exception e){
e.printStackTrace();
        }
    }

    public void printLog(){
        IDatabaseHelper databaseHelper = DatabaseHelperSingleton.getDatabaseHelper();
        List<Beehive> listOfBeehives = databaseHelper.getAllBeehives();
        List<Apiary> listOfApiaries = databaseHelper.getAllApiaries();
        List<Queen> queenList = databaseHelper.getAllQueens();

        System.out.println("----DATABASE-FILLED-WITH-SAMPLE-DATA-------");
        System.out.println("------LISTING-DATABASE-CONTENTS------------");
        System.out.println("------LISTING-APIARIES---------------------");

        for (Apiary a : listOfApiaries) {
            System.out.println(a);
        }

        System.out.println("------LISTING-QUEENS----------------------");

        for (Queen q : queenList){
            System.out.println(q);
        }

        System.out.println("------LISTING-BEEHIVES--------------------");

        for (Beehive b : listOfBeehives) {
            System.out.println(b);
        }

        System.out.println("------LISTING-BEEHIVES-IN-APIARIES--------");
        for (Apiary a : listOfApiaries) {
            System.out.println(a);
                    List<Beehive> beehiveList = databaseHelper.getBeehivesFromApiary(a);
                    for(Beehive b : beehiveList){
                        System.out.println("----> " + b);
                    }
        }



        System.out.println("------LISTING-STORAGE ---------------------");
        System.out.println(databaseHelper.getStorage());

        System.out.println("------LISTING-BEEHIVES-IN-STORAGE---------");
        System.out.println(databaseHelper.getBeehivesFromStorage());
    }
}
